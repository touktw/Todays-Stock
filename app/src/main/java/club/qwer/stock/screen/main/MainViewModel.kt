package club.qwer.stock.screen.main

import androidx.lifecycle.viewModelScope
import club.qwer.stock.base.ActivityViewModel
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.usecase.GetRandomStockListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomStockListUseCase: GetRandomStockListUseCase
) : ActivityViewModel() {

    private val _stockInfoList = MutableStateFlow<List<StockInfo>>(emptyList())
    val stockInfoList: StateFlow<List<StockInfo>> get() = _stockInfoList

    fun loadInfo() {
        viewModelScope.launch {
            getRandomStockListUseCase()
                .onEach {
                    Timber.d("result:${it.size}")
                }
                .catch { }
                .stateIn(scope = viewModelScope)
                .collect()

        }
    }
}