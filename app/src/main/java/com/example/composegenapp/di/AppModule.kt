package com.example.composegenapp.di

import android.content.Context
import com.example.composegenapp.common.Constants
import com.example.composegenapp.db.FalconInfoDao
import com.example.composegenapp.db.FalconInfoDatabase
import com.example.composegenapp.remote.ApiService
import com.example.composegenapp.remote.RemoteDataSourceImpl
import com.galeryalina.domain.repository.FalconRepository
import com.example.composegenapp.domain.domain.repository.FalconRepositoryImpl
import com.galeryalina.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): FalconInfoDatabase = FalconInfoDatabase.getDatabase(appContext, CoroutineScope(SupervisorJob()))

    @Singleton
    @Provides
    fun provideFalconInfoDao(db: FalconInfoDatabase): FalconInfoDao = db.dao()

    @Singleton
    @Provides
    fun provideKtorfit(): Ktorfit {

        val ktorfit = ktorfit {
            baseUrl(Constants.BASE_URL)
            httpClient(HttpClient {
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true })
                }
            })
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )

        }
        return ktorfit
    }

    @Singleton
    @Provides
    fun provideApiServices(ktorfitClient: Ktorfit): ApiService {
        return ktorfitClient.create<ApiService>()
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApiService, localDataSource: LocalDataSource
    ): FalconRepository {
        val remoteDataSourceImpl = RemoteDataSourceImpl(api)
        return FalconRepositoryImpl(remoteDataSourceImpl, localDataSource = localDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: FalconInfoDao): LocalDataSource {
        return LocalDataSource(dao)
    }

}
