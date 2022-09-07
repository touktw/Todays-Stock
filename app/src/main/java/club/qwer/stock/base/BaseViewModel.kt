package club.qwer.stock.base

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


open class FragmentViewModel : BaseViewModel()
open class ActivityViewModel : BaseViewModel()

abstract class BaseViewModel : ViewModel() {

    // 그대로 쓰지 말자, private 로 하고싶은데.. constructor 에 context 를 박아?
    @Inject
    protected lateinit var stringResource: StringResource

    fun newStringResource() = stringResource.copy()

    private val _viewEventFlow = MutableSharedFlow<ViewEvent>()
    val viewEventFlow: SharedFlow<ViewEvent> get() = _viewEventFlow.asSharedFlow()

    fun showToast(@StringRes resId: Int) {
        viewModelScope.launch {
            _viewEventFlow.emit(
                ViewEvent.Toast(
                    stringResource.copy(
                        resId = resId
                    )
                )
            )
        }
    }

    fun showToast(message: String) {
        viewModelScope.launch {
            _viewEventFlow.emit(
                ViewEvent.Toast(
                    stringResource.copy(
                        value = message
                    )
                )
            )
        }
    }
}

sealed class ViewEvent {
    data class Toast(val stringResource: StringResource) : ViewEvent()
}

data class StringResource(
    val context: Context,
    @StringRes val resId: Int? = null,
    val value: String = ""
) {
    fun string(): String {
        return when {
            resId != null -> context.getString(resId)
            else -> value
        }
    }
}