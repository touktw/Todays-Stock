package club.qwer.stock.data.source.remote.api

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class ServiceProviderModule {

    @Provides
    @Singleton
    fun provideStockServiceProvider() = StockServiceProvider()
}