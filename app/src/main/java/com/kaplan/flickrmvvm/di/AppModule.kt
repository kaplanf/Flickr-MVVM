package com.kaplan.flickrmvvm.di

import android.app.Application
import com.kaplan.flickrmvvm.api.PhotoService
import com.kaplan.flickrmvvm.data.AppDatabase
import com.kaplan.flickrmvvm.recent.data.PhotosRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

  @Singleton
  @Provides
  fun providePhotoService(okhttpClient: OkHttpClient,
                          converterFactory: GsonConverterFactory
  ) = provideService(okhttpClient, converterFactory, PhotoService::class.java)

  @Singleton
  @Provides
  fun providePhotosRemoteDataSource(photoService: PhotoService) =
    PhotosRemoteDataSource(photoService)

  @Singleton
  @Provides
  fun provideDb(app: Application) = AppDatabase.getInstance(app)

  @Singleton
  @Provides
  fun providePhotoDao(db: AppDatabase) = db.photoDao()

  @CoroutineScropeIO
  @Provides
  fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

  @CoroutineScropeMain
  @Provides
  fun provideCoroutineScopeMain() = CoroutineScope(Dispatchers.Main)

  private fun createRetrofit(
    okhttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(PhotoService.ENDPOINT)
      .client(okhttpClient)
      .addConverterFactory(converterFactory)
      .build()
  }

  private fun <T> provideService(okhttpClient: OkHttpClient,
                                 converterFactory: GsonConverterFactory, clazz: Class<T>): T {
    return createRetrofit(okhttpClient, converterFactory).create(clazz)
  }
}
