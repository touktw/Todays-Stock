package club.qwer.stock.data.repository

import com.skydoves.sandwich.ApiResponse

internal abstract class BaseRepository<SVC>(val service: SVC) {
    protected suspend fun <T> call(apiCall: suspend () -> ApiResponse<T>): T {
        when(val response = apiCall.invoke()) {
            is ApiResponse.Success -> response.data
            is ApiResponse.Failure.Error -> {
                // handle error
            }
            is ApiResponse.Failure.Exception -> {
                // handle exception
            }
        }

    }
}

