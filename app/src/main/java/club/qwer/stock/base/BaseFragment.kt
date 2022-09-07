package club.qwer.stock.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private lateinit var _viewModel: VM
    protected val viewModel: VM get() = _viewModel
    private lateinit var _viewBinding: VB
    protected val viewBinding: VB get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType

        val view = initializeView(
            parameterizedType.actualTypeArguments[0] as Class<VB>,
            inflater,
            container
        )

        initializeViewModel(parameterizedType.actualTypeArguments[1] as Class<VM>)

        return view
    }

    private fun initializeView(
        viewBindingClass: Class<VB>,
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): View {
        val inflateMethod = viewBindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        _viewBinding = inflateMethod.invoke(viewBindingClass, inflater, container, false) as VB
        return _viewBinding.root
    }

    private fun initializeViewModel(viewModelClass: Class<VM>) {
        _viewModel = when (viewModelClass.javaClass.genericSuperclass) {
            is ActivityViewModel -> {
                ViewModelProvider(requireActivity()).get(viewModelClass)
            }
            else -> ViewModelProvider(this).get(viewModelClass)
        }
        bindViewModel()
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewEventFlow
                .onEach {
                    when (it) {
                        is ViewEvent.Toast -> {
                            Toast.makeText(
                                this@BaseFragment.context,
                                it.stringResource.string(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {}
                    }
                }
                .collect()
        }
    }

    protected fun navigate(
        @IdRes
        resId: Int
    ) {
        findNavController().navigate(resId)
    }

    protected fun showProgress() {
        (activity as? BaseActivity<*, *>)?.showProgress()
    }

    protected fun hideProgress() {
        (activity as? BaseActivity<*, *>)?.hideProgress()
    }
}