package com.kaplan.flickrmvvm.api


import com.kaplan.flickrmvvm.recent.data.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

  companion object {
    const val ENDPOINT = "https://www.flickr.com/services/rest/"
  }

  @GET("?method=flickr.photos.getRecent")
  suspend fun getRecents(@Query("page") page: Int? = null,
                         @Query("per_page") perPage: Int? = null,
                         @Query("format") format: String? = null,
                         @Query("nojsoncallback") nojsoncallback: Int,
                         @Query("api_key") api_key: String? = null): Response<PhotosResponse>
}
/*

api_key=ce3bd4def7809b2bc9606df2d0067b7a&per_page=&page=&format=json&nojsoncallback=1&api_sig=de5b98c681cef59be2064ebba69cd3bd

*/