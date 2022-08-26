package club.qwer.stock.domain.model

data class StockInfo(
    val name: String,
    val code: String,
    val marketCap: Long,
    val openPrice: Long,
    val closingPrice: Long,
    val highPrice: Long,
    val lowPrice: Long,
    val marketType: StockMarketType
)

enum class StockMarketType {
    KOSPI,
    KOSDAQ,
    KONEX,
    UNKNOWN;

    companion object {
        fun get(value: String): StockMarketType {
            return try {
                valueOf(value)
            } catch (e: Exception) {
                UNKNOWN
            }
        }
    }
}