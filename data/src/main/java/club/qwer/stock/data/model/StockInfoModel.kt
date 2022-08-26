package club.qwer.stock.data.model

import club.qwer.stock.data.source.remote.dto.response.StockPriceInfoResponse

data class StockInfoModel(
    val name: String,
    val code: String,
    val marketCap: Long,
    val openPrice: Long,
    val closingPrice: Long,
    val lowPrice: Long,
    val highPrice: Long,
    val marketType: String
)

fun StockPriceInfoResponse.StockPriceInfoDto.toStockInfoModel(): StockInfoModel {
    return StockInfoModel(
        name = name,
        code = code,
        marketCap = marketTotalAmount,
        openPrice = openPrice,
        closingPrice = closingPrice,
        lowPrice = lowPrice,
        highPrice = highPrice,
        marketType = marketType
    )
}