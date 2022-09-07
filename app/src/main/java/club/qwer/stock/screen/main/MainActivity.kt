package club.qwer.stock.screen.main

import android.os.Bundle
import club.qwer.stock.base.BaseActivity
import club.qwer.stock.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadInfo()
    }

    override fun onResume() {
        super.onResume()
        viewModel.showToast("Test")
    }
}