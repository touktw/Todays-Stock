package club.qwer.stock.data.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

internal abstract class BaseRepository<SVC>(val service: SVC) {
    protected suspend fun <T> call(apiCall: suspend () -> ApiResponse<T>): T {
        return when(val response = apiCall.invoke()) {
            is ApiResponse.Success -> response.data
            is ApiResponse.Failure.Error -> {
                // handle error
                throw Exception(response.message())
            }
            is ApiResponse.Failure.Exception -> {
                // handle exception
                throw Exception(response.message())
            }
        }
    }
}

