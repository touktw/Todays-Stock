package club.qwer.stock.data.source.remote.api

import club.qwer.stock.data.source.remote.api.client.BaseServiceProvider
import javax.inject.Inject

class StockServiceProvider @Inject constructor() : BaseServiceProvider<StockService>(
    serviceClass = StockService::class.java,
    baseUrl = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/"
)

//
//@Module
//@InstallIn(SingletonComponent::class)
//object BaseServiceProviderModule {
//
//    @Provides
//    fun provideBaseServiceProvider(provider:StockServiceProvider) : BaseServiceProvider {
//        return provider
//    }
//}