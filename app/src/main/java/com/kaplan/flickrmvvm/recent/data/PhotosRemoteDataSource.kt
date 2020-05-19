package com.kaplan.flickrmvvm.recent.data


import com.kaplan.flickrmvvm.BuildConfig
import com.kaplan.flickrmvvm.api.BaseDataSource
import com.kaplan.flickrmvvm.api.PhotoService
import javax.inject.Inject

class PhotosRemoteDataSource @Inject constructor(private val service: PhotoService) : BaseDataSource() {

    suspend fun fetchSets(page: Int, pageSize: Int? = null)
            = getResult { service.getRecents(page, pageSize, "json", 1, BuildConfig.client_key) }

}
