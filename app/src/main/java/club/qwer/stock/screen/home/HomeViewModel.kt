package club.qwer.stock.screen.home

import androidx.lifecycle.viewModelScope
import club.qwer.stock.base.ActivityViewModel
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.usecase.GetRandomStockListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomStockListUseCase: GetRandomStockListUseCase
) : ActivityViewModel() {
    private val _stockInfoList = MutableStateFlow<List<StockInfo>>(emptyList())
    val stockInfoList: StateFlow<List<StockInfo>> get() = _stockInfoList

    fun loadStockInfo() {
        viewModelScope.launch {
            getRandomStockListUseCase()
                .onEach {
                    _stockInfoList.emit(it)
                }
                .catch { }
                .stateIn(scope = viewModelScope)
                .collect()

        }
    }
}