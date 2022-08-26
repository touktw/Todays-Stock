package club.qwer.stock.data.source.remote.api

import club.qwer.stock.data.source.remote.api.client.BaseApiServiceFactory
import javax.inject.Inject

class StockPriceApiServiceFactory @Inject constructor() : BaseApiServiceFactory<StockPriceApiService>(
    serviceClass = StockPriceApiService::class.java,
    baseUrl = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/"
)