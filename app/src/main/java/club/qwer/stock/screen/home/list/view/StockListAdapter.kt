package club.qwer.stock.screen.home.list.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import club.qwer.stock.domain.model.StockInfo

class StockListAdapter : ListAdapter<StockInfo, StockListAdapter.StockListViewHolder>(
    object : DiffUtil.ItemCallback<StockInfo>() {
        override fun areItemsTheSame(oldItem: StockInfo, newItem: StockInfo): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: StockInfo, newItem: StockInfo): Boolean {
            return oldItem == newItem
        }
    }
) {


    class StockListViewHolder(private val stockListItemView: StockListItemView) :
        RecyclerView.ViewHolder(stockListItemView) {

        fun onBindViewHolder(item: StockInfo) {
            stockListItemView.setItem(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        return StockListViewHolder(
            StockListItemView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        )
    }

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBindViewHolder(item)
    }
}