package club.qwer.stock.domain.repository

import club.qwer.stock.domain.model.StockInfo

interface StockRepository {
    suspend fun getStockInfoList(likeCode: Int? = null): List<StockInfo>
}