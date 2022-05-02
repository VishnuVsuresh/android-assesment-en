package com.example.catknowledgebase.di

import android.content.Context
import androidx.room.Room
import com.example.catknowledgebase.api.FactApi
import com.example.catknowledgebase.data.local.FactDao
import com.example.catknowledgebase.data.local.FactDataBase
import com.example.catknowledgebase.data.local.FactRepository
import com.example.catknowledgebase.data.local.FactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFavMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FactDataBase::class.java,
        "fact_db"
    ).build()


    @Singleton
    @Provides
    fun provideFactDao(db: FactDataBase) = db.getFactDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(FactApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideFactApi(retrofit: Retrofit): FactApi =
        retrofit.create(FactApi::class.java)


    @Provides
    @Singleton
    fun provideRepository(apiHelper: FactApi,dao: FactDao)= FactRepositoryImpl(factDao = dao, factApi = apiHelper) as FactRepository

}