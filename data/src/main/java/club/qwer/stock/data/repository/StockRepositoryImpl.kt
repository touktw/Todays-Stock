package club.qwer.stock.data.repository

import club.qwer.stock.data.mapper.StockInfoMapper
import club.qwer.stock.data.source.remote.api.StockPriceApiService
import club.qwer.stock.data.source.remote.api.StockPriceApiServiceFactory
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.repository.StockRepository
import javax.inject.Inject

internal class StockRepositoryImpl @Inject constructor(stockServiceProvider: StockPriceApiServiceFactory) :
    BaseRepository<StockPriceApiService>(stockServiceProvider.getService()), StockRepository {
    private val stockInfoMapper = StockInfoMapper()

    override suspend fun getStockInfoList(likeCode: Int?): List<StockInfo> {
        return call { service.getStockPriceInfo() }.response.body.items.item.map {
            stockInfoMapper(it)
        }
    }
}