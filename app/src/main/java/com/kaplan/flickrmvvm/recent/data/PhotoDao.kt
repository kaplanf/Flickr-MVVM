package com.kaplan.flickrmvvm.recent.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {

  @Query("SELECT * FROM recents ORDER BY id DESC")
  fun getPhotos(): LiveData<List<Photo>>

  @Query("SELECT * FROM recents ORDER BY id DESC")
  fun getPagedPhotos(): DataSource.Factory<Int, Photo>

  @Query("SELECT * FROM recents WHERE id = :id")
  fun getPhoto(id: String): LiveData<Photo>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(photos: List<Photo>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(photo: Photo)
}
