package club.qwer.stock.data.mapper

import club.qwer.stock.data.source.remote.dto.response.StockPriceInfoResponse
import club.qwer.stock.domain.model.StockInfo
import club.qwer.stock.domain.model.StockMarketType

class StockInfoMapper {
    operator fun invoke(stockInfoDto: StockPriceInfoResponse.StockPriceInfoDto): StockInfo {
        return StockInfo(
            name = stockInfoDto.name,
            code = stockInfoDto.code,
            marketCap = stockInfoDto.marketTotalAmount,
            openPrice = stockInfoDto.openPrice,
            closingPrice = stockInfoDto.closingPrice,
            lowPrice = stockInfoDto.lowPrice,
            highPrice = stockInfoDto.highPrice,
            marketType = StockMarketType.get(stockInfoDto.marketType)
        )
    }
}