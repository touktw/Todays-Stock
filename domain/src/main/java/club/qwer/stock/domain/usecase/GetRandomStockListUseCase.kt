package club.qwer.stock.domain.usecase

import club.qwer.stock.data.repository.StockRepository
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.model.toStockInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class GetRandomStockListUseCase @Inject constructor(
    private val stockRepository: StockRepository
) {
    companion object {
        private val STRN_CODE_RANGE = 100..999
    }

    operator fun invoke(): Flow<List<StockInfo>> {
        return flow {
            val randomCode = Random(System.currentTimeMillis()).nextInt(STRN_CODE_RANGE)
            val list = stockRepository.getStockInfoList(
                likeCode = randomCode
            ).map { it.toStockInfo() }
            emit(list)
        }.flowOn(Dispatchers.Default)
    }
}