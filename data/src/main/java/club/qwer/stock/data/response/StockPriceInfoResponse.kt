package club.qwer.stock.data.response

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
        val itmsNm: String, // 종목명
        val srtnCd: String, // 식별코드 (6자)
        val mrktTotAmt: Long, // 종가 * 상장주식수
        val mrktCtg: String, // 주식 시장구분 (KOSPI , KOSDAQ , KONEX)
        val clpr: String, // 정규시장의 매매 종료시까지 형성되는 최종가격
        val vs: Long, // 전일 대비 등락
        val mkp: Long, // 정규시장의 매매시간 개시후 형성되는 최초 가격
        val hipr: Long, // 가격 최고치
        val lopr: Long, // 가격 최저치,
        val trqu: Long, // 누적 체결수량,
        val lstgStCnt: Long, // 종목의 상장 주식수
        val basDt: String, // 기준일자 20220822 yyyyMMdd
        val isinCd: String, // 국제 채권 식별 번호
    )
}