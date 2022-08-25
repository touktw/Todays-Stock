package club.qwer.stock.data.di.module

import club.qwer.stock.data.repository.StockRepository
import club.qwer.stock.data.repository.StockRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindStockRepository(stockRepository: StockRepositoryImpl): StockRepository
}