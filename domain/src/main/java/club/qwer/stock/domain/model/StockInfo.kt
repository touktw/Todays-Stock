package club.qwer.stock.domain.model

import java.util.*

data class StockInfo(
    val name: String,
    val price: Long,
    val code: String,
    val callPrice:String,
    val highPrice:Long,
    val lowPrice:Long,
    val date: Date
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