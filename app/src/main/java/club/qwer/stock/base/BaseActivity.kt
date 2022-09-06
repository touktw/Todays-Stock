package club.qwer.stock.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    private lateinit var _viewModel: VM
    protected val viewModel: VM get() = _viewModel
    private lateinit var _viewBinding: VB
    protected val viewBinding: VB get() = _viewBinding


    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get parameterized type
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType

        // get viewbinding class and inflate it
        initializeView(parameterizedType.actualTypeArguments[0] as Class<VB>)

        // get viewmodel class and binding it
        initializeViewModel(parameterizedType.actualTypeArguments[1] as Class<VM>)
    }

    private fun initializeView(viewBindingClass: Class<VB>) {
        val inflateMethod = viewBindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )
        _viewBinding = inflateMethod.invoke(viewBindingClass, LayoutInflater.from(this)) as VB
        setContentView(_viewBinding.root)
    }

    private fun initializeViewModel(viewModelClass: Class<VM>) {
        _viewModel = ViewModelProvider(this)[viewModelClass]
        bindViewModel()
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenStarted {
            _viewModel.viewEventFlow
                .onEach {
                    when (it) {
                        is ViewEvent.Toast -> {
                            Toast.makeText(
                                this@BaseActivity,
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
}