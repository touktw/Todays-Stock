package club.qwer.stock.data.source.remote.api

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object ServiceFactory {
    fun <T> createService(
        serviceClass: Class<T>,
        baseUrl: String,
        socketTimeoutSec: Long = 60L,
        headerInterceptor: Interceptor = DefaultHeaderInterceptor(),
        isDebug: Boolean = false
    ): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(createOkHttpClient(headerInterceptor, socketTimeoutSec, isDebug))
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build().create(serviceClass)
    }

    private fun createOkHttpClient(
        headerInterceptor: Interceptor,
        socketTimeoutSec: Long,
        isDebug: Boolean
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor {}.apply {
                level =
                    if (isDebug) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(socketTimeoutSec, TimeUnit.SECONDS)
            .readTimeout(socketTimeoutSec, TimeUnit.SECONDS)
            .writeTimeout(socketTimeoutSec, TimeUnit.SECONDS)
            .build()
    }

    private class DefaultHeaderInterceptor : Interceptor {
        private val defaultHeader = mutableMapOf(
            "Accept" to "application/json;charset=UTF-8",
            "Content-type" to "application/json;charset=UTF-8",
        )

        override fun intercept(chain: Interceptor.Chain): Response {
            val newBuilder = chain.request().newBuilder().apply {
                defaultHeader.forEach { entry ->
                    addHeader(entry.key, entry.value)
                }
            }
            return chain.proceed(newBuilder.build())
        }
    }

    private class NullOnEmptyConverterFactory : Converter.Factory() {

        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter<ResponseBody, Any> { body ->
                if (body.contentLength() == 0L) Any() else delegate.convert(
                    body
                )
            }
        }
    }
}