package club.qwer.stock.data.source.remote.api

import club.qwer.stock.data.response.StockPriceInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {
    companion object {
        private const val SERVICE_KEY =
            "ynoWq1I6T96xXvG7Hpc3UuR4eNJYUq1gzuQ7ATN7oU8%2FwOyiqMiSFFbwTxgoe1ZKtnk2qsljTtry%2BlX3MbWiuw%3D%3D"
        private const val KEY2 = "ynoWq1I6T96xXvG7Hpc3UuR4eNJYUq1gzuQ7ATN7oU8/wOyiqMiSFFbwTxgoe1ZKtnk2qsljTtry+lX3MbWiuw=="
    }

    @GET("getStockPriceInfo")
    suspend fun getStockPriceInfo(
        @Query("likeSrtnCd") likeSrtnCode: Int? = null,
        @Query("likeItmsNm") likeItemName: String? = null,
        @Query("resultType") resultType: String = "json",
        @Query("serviceKey") serviceKey: String = KEY2,
        @Query("numOfRows") offsetCount: Int = 10,
        @Query("pageNo") pageNumber: Int? = null
    ): StockPriceInfoResponse
}