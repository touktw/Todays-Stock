package club.qwer.stock.data.source.remote.dto.response

import com.squareup.moshi.Json

data class StockPriceInfoResponse(
    val response: Response
) {

    data class Response(
        val header: HeaderDto,
        val body: StockPriceInfoListDto
    )

    data class HeaderDto(
        val resultCode: String,
        val resultMsg: String,
    )

    data class StockPriceInfoListDto(
        val numOfRows: Int,
        val pageNo: Int,
        val totalCount: Int,
        val items: StockPriceInfoItems
    )

    data class StockPriceInfoItems(
        val item: List<StockPriceInfoDto>
    )

    data class StockPriceInfoDto(
        @Json(name = "itmsNm")
        val name: String, // 종목명
        @Json(name = "srtnCd")
        val code: String, // 식별코드 (6자)
        @Json(name = "mrktTotAmt")
        val marketTotalAmount: Long, // 종가 * 상장주식수 (시총)
        @Json(name = "mrktCtg")
        val marketType: String, // 주식 시장구분 (KOSPI , KOSDAQ , KONEX)
        @Json(name = "clpr")
        val closingPrice: Long, // 정규시장의 매매 종료시까지 형성되는 최종가격, 종가
        @Json(name = "vs")
        val vs: Long, // 전일 대비 등락
        @Json(name = "mkp")
        val openPrice: Long, // 정규시장의 매매시간 개시후 형성되는 최초 가격
        @Json(name = "hipr")
        val highPrice: Long, // 가격 최고치
        @Json(name = "lopr")
        val lowPrice: Long, // 가격 최저치,
        @Json(name = "trqu")
        val tradingQuantity: Long, // 누적 체결수량,
        @Json(name = "lstgStCnt")
        val stockCount: Long, // 종목의 상장 주식수
        @Json(name = "basDt")
        val baseDate: String, // 기준일자 20220822 yyyyMMdd
        @Json(name = "isinCd")
        val isinCode: String, // 국제 채권 식별 번호
    )
}