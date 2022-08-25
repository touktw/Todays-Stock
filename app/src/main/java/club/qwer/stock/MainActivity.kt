package club.qwer.stock

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.qwer.stock.databinding.ActivityMainBinding
import club.qwer.stock.domain.usecase.GetStockListUseCase
import dagger.hilt.android.AndroidEntryPoint
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