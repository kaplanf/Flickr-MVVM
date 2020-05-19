package com.kaplan.flickrmvvm.recent.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PhotosRepository @Inject constructor(private val dao: PhotoDao,
                                           private val photosRemoteDataSource: PhotosRemoteDataSource) {

  fun observePagedSets(connectivityAvailable: Boolean,
                       coroutineScope: CoroutineScope) =
    if (connectivityAvailable) {
      observeRemotePagedSets(coroutineScope)
    } else {
      observeLocalPagedSets()
    }

  private fun observeLocalPagedSets(): LiveData<PagedList<Photo>> {
    val dataSourceFactory = dao.getPagedPhotos()
    return LivePagedListBuilder(dataSourceFactory,
      PhotosPageDataSourceFactory.pagedListConfig()).build()
  }

  private fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope)
      : LiveData<PagedList<Photo>> {
    val dataSourceFactory = PhotosPageDataSourceFactory(photosRemoteDataSource,
      dao, ioCoroutineScope)
    return LivePagedListBuilder(dataSourceFactory,
      PhotosPageDataSourceFactory.pagedListConfig()).build()
  }

  companion object {

    const val PAGE_SIZE = 100

    // For Singleton instantiation
    @Volatile
    private var instance: PhotosRepository? = null

    fun getInstance(dao: PhotoDao, photosRemoteDataSource: PhotosRemoteDataSource) =
      instance ?: synchronized(this) {
        instance
          ?: PhotosRepository(dao, photosRemoteDataSource).also { instance = it }
      }
  }
}
