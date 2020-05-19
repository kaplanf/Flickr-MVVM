package com.kaplan.flickrmvvm.recent.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recents")
data class Photo(
  @field:PrimaryKey(autoGenerate = true)
  var id: Long,
  var owner: String,
  var secret: String,
  var server: String,
  var farm: Int,
  var title: String,
  var ispublic: Int,
  var isfriend: Int,
  var isfamily: Int): Serializable

data class Photos(val photo: List<Photo>,
                  val page: Int,
                  val pages: Int,
                  val perpage: Int,
                  val total: String)

data class PhotosResponse(val photos: Photos)