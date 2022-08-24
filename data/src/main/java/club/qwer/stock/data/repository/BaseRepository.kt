package club.qwer.stock.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

internal abstract class BaseRepository<SVC>(val service: SVC) {
    protected suspend fun <T> call(apiCall: suspend () -> T): T {
        val value = apiCall.invoke()
        return value
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindStockRepository(stockRepository: StockRepositoryImpl): StockRepository
}

