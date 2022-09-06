package club.qwer.stock.screen.home.list.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import club.qwer.stock.databinding.ViewStockListItemBinding
import club.qwer.stock.domain.model.StockInfo

class StockListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val viewBinding =
        ViewStockListItemBinding.inflate(LayoutInflater.from(context), this)

    fun setItem(item: StockInfo) {
        viewBinding.nameTextView.text = item.name
    }
}