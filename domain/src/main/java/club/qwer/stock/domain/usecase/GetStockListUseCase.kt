package club.qwer.stock.domain.usecase

import club.qwer.stock.data.repository.StockRepository
import club.qwer.stock.data.source.remote.dto.response.StockPriceInfoResponse
import timber.log.Timber
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val stockRepository: StockRepository
) {
    suspend operator fun invoke(pageNum: Int? = null): List<StockPriceInfoResponse.StockPriceInfoDto> {
        return try {
            val list = stockRepository.getStockInfo(pageNum)
            return list
        } catch (e: Exception) {
            Timber.d("### e:${e.message}")
            emptyList()
        }
    }
}