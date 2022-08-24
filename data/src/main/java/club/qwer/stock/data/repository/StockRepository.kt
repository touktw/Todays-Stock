package club.qwer.stock.data.repository

import club.qwer.stock.data.response.StockPriceInfoResponse

interface StockRepository {
    suspend fun getStockInfo():List<StockPriceInfoResponse.StockPriceInfoDto>
}