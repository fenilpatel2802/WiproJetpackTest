package com.wipro.wiprojetpacktest.di

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wipro.wiprojetpacktest.core.utils.Constants
import com.wipro.wiprojetpacktest.data.remote.network.ApiService
import com.wipro.wiprojetpacktest.data.repository.GetCategoryRepositoryImpl
import com.wipro.wiprojetpacktest.data.repository.GetProductRepositoryImpl
import com.wipro.wiprojetpacktest.domain.repository.GetCategoryRepository
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetCategoryWiseProductUseCase
import com.wipro.wiprojetpacktest.domain.use_cases.GetProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    // region retrofit
    @Provides
    internal fun provideGson(): Gson =
        GsonBuilder().apply {
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }.create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okClient = OkHttpClient.Builder()
        okClient.connectTimeout(60L, TimeUnit.SECONDS)
        okClient.writeTimeout(60L, TimeUnit.SECONDS)
        okClient.readTimeout(60L, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okClient.interceptors().add(interceptor)

        okClient.interceptors().add(Interceptor { chain ->
            val response = chain.proceed(chain.request())
            response.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build()
            response
        })
        return okClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    // endregion

    @Provides
    fun providesGetProductRepository(apiService: ApiService): GetProductRepository {
        return GetProductRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetCategoryRepository(apiService: ApiService): GetCategoryRepository {
        return GetCategoryRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetProductUseCase(getProductRepository: GetProductRepository): GetProductUseCase {
        return GetProductUseCase(getProductRepository)
    }

    @Provides
    fun providesGetCategoryUseCase(getCategoryRepository: GetCategoryRepository): GetCategoryUseCase {
        return GetCategoryUseCase(getCategoryRepository)
    }

    @Provides
    fun providesGetCategoryWiseProductUseCase(getProductRepository: GetProductRepository): GetCategoryWiseProductUseCase {
        return GetCategoryWiseProductUseCase(getProductRepository)
    }

}