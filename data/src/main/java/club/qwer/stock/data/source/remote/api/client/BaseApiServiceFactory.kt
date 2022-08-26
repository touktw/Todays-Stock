package club.qwer.stock.data.source.remote.api.client

import club.qwer.stock.data.BuildConfig
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

abstract class BaseApiServiceFactory<T>(
    private val serviceClass: Class<T>,
    private val baseUrl: String,
    private val headerInterceptor: Interceptor = DefaultHeaderInterceptor()
) {

    open val defaultSocketTimeoutSec = 60L
    private lateinit var retrofit: Retrofit
    private var service: T

    init {
        service = createService()
    }

    fun getService(): T {
        return service
    }

    private fun createService(): T {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor {}.apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(defaultSocketTimeoutSec, TimeUnit.SECONDS)
            .readTimeout(defaultSocketTimeoutSec, TimeUnit.SECONDS)
            .writeTimeout(defaultSocketTimeoutSec, TimeUnit.SECONDS)
            .build()
    }


    protected class DefaultHeaderInterceptor : Interceptor {
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

    companion object {
        protected val defaultHeader = mutableMapOf(
            "Accept" to "application/json;charset=UTF-8",
            "Content-type" to "application/json;charset=UTF-8",
        )
    }

}