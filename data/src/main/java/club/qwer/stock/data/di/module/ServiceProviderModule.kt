package club.qwer.stock.data.di.module

import club.qwer.stock.data.source.remote.api.ServiceFactory
import club.qwer.stock.data.source.remote.api.StockPriceApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class ApiServiceModule {

    @Provides
    @Singleton
    fun provideStockPriceApiService() = ServiceFactory.createService(
        serviceClass = StockPriceApiService::class.java,
        baseUrl = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/"
    )
}