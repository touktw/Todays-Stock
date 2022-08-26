package club.qwer.stock.data.repository

import club.qwer.stock.data.model.StockInfoModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStockInfoList(likeCode: Int? = null): List<StockInfoModel>
}