package club.qwer.stock.data.repository

import club.qwer.stock.data.mapper.StockInfoMapper
import club.qwer.stock.data.source.remote.api.StockPriceApiService
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.repository.StockRepository
import javax.inject.Inject

internal class StockRepositoryImpl @Inject constructor(private val stockPriceApiService: StockPriceApiService) :
    BaseRepository(), StockRepository {
    private val stockInfoMapper = StockInfoMapper()

    override suspend fun getStockInfoList(likeCode: Int?): List<StockInfo> {
        return call { stockPriceApiService.getStockPriceInfo() }.response.body.items.item.map {
            stockInfoMapper(it)
        }
    }
}