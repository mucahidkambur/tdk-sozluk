package com.mucahitkambur.tdksozluk.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mucahitkambur.tdksozluk.BuildConfig
import com.mucahitkambur.tdksozluk.network.api.ApiService
import com.mucahitkambur.tdksozluk.network.local.AppDatabase
import com.mucahitkambur.tdksozluk.network.local.SuggestionDao
import com.mucahitkambur.tdksozluk.util.LiveDataCallAdapterFactory
import com.mucahitkambur.tdksozluk.util.PreferenceStorage
import com.mucahitkambur.tdksozluk.util.SharedPreferenceStorage
import com.mucahitkambur.tdksozluk.util.hasNetwork
import com.mucahitkambur.tdksozluk.util.ignoreAllSSLErrors
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module(includes = [ViewModelModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase{
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAutocompDao(database: AppDatabase): SuggestionDao {
        return database.suggestionDao()
    }

    @Singleton
    @Provides
    fun provideInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 1).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                .newBuilder()
                .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor, application: Application): OkHttpClient {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(application.applicationContext.cacheDir, cacheSize)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .cache(myCache)
            .ignoreAllSSLErrors()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(httpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(httpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage =
        SharedPreferenceStorage(context)

}