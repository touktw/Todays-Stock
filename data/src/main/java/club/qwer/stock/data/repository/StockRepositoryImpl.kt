package club.qwer.stock.data.repository

import club.qwer.stock.data.model.StockInfoModel
import club.qwer.stock.data.model.toStockInfoModel
import club.qwer.stock.data.source.remote.api.StockPriceApiService
import club.qwer.stock.data.source.remote.api.StockPriceApiServiceFactory
import javax.inject.Inject

internal class StockRepositoryImpl @Inject constructor(stockServiceProvider: StockPriceApiServiceFactory) :
    BaseRepository<StockPriceApiService>(stockServiceProvider.getService()), StockRepository {

    override suspend fun getStockInfoList(likeCode: Int?): List<StockInfoModel> {
        return call { service.getStockPriceInfo() }.response.body.items.item.map { it.toStockInfoModel() }
    }
}