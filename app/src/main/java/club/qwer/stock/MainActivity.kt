package club.qwer.stock

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.qwer.stock.data.repository.StockRepository
import club.qwer.stock.data.response.StockPriceInfoResponse
import club.qwer.stock.databinding.ActivityMainBinding
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadInfo()
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStockListUseCase: GetStockListUseCase
) : ViewModel() {


    fun loadInfo() {
        viewModelScope.launch {
            val result = getStockListUseCase()
            Timber.d("result:${result.size}")
        }
    }
}

class GetStockListUseCase @Inject constructor(
    private val stockRepository: StockRepository
) {
    suspend operator fun invoke(): List<StockPriceInfoResponse.StockPriceInfoDto> {
        return try {
            val list = stockRepository.getStockInfo()
            return list
        } catch (e: Exception) {
            Timber.d("### e:${e.message}")
            emptyList()
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun getStockListUseCaseBind(getStockListUseCase: GetStockListUseCase): GetStockListUseCase
}