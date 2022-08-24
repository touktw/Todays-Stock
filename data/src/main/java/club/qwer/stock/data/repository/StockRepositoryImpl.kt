package club.qwer.stock.data.repository

import club.qwer.stock.data.response.StockPriceInfoResponse
import club.qwer.stock.data.source.remote.api.StockService
import club.qwer.stock.data.source.remote.api.StockServiceProvider
import timber.log.Timber
import javax.inject.Inject

internal class StockRepositoryImpl @Inject constructor(stockServiceProvider: StockServiceProvider) :
    BaseRepository<StockService>(stockServiceProvider.getService()), StockRepository {

    override suspend fun getStockInfo(): List<StockPriceInfoResponse.StockPriceInfoDto> {
        val response = service.getStockPriceInfo()
        Timber.d("### response:${response}")
        return response.response.body.items.item
    }
}