package club.qwer.stock.screen.home.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import club.qwer.stock.base.BaseFragment
import club.qwer.stock.databinding.FragmentStockListBinding
import club.qwer.stock.screen.home.HomeViewModel
import club.qwer.stock.screen.home.list.view.StockListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StockListFragment : BaseFragment<FragmentStockListBinding, HomeViewModel>() {
    private val adapter = StockListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.refreshTextView.setOnClickListener { viewModel.loadStockInfo() }
        viewBinding.recyclerView.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel.stockInfoList
                .onEach {
                    adapter.submitList(it)
                }
                .collect()
        }
    }
}