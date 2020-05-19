package com.kaplan.flickrmvvm.recent.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaplan.flickrmvvm.databinding.PhotoListItemBinding
import com.kaplan.flickrmvvm.recent.data.Photo


class PhotosAdapter : PagedListAdapter<Photo, PhotosAdapter.ViewHolder>(PhotoDiffCallback()) {

  private lateinit var recyclerView: RecyclerView

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val photo = getItem(position)
    photo?.let {
      holder.apply {
        bind(createOnClickListener(position), photo, isGridLayoutManager())
        itemView.tag = photo
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(PhotoListItemBinding.inflate(
      LayoutInflater.from(parent.context), parent, false))
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    this.recyclerView = recyclerView
  }

  private fun createOnClickListener(position: Int): View.OnClickListener {
    return View.OnClickListener {
      //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
      val photo = getItem(position) as Photo
      val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + "_b.jpg"
      val direction = RecentFragmentDirections.actionToDetailFragment(url)
      it.findNavController().navigate(direction)
    }
  }

  private fun isGridLayoutManager() = recyclerView.layoutManager is GridLayoutManager

  class ViewHolder(private val binding: PhotoListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: View.OnClickListener, item: Photo,
             isGridLayoutManager: Boolean) {
      binding.apply {
        url  = "https://farm" + item?.farm + ".staticflickr.com/" + item?.server + "/" + item?.id + "_" + item?.secret + "_s.jpg"
        clickListener = listener
        photo = item
        title.visibility = if (isGridLayoutManager) View.GONE else View.VISIBLE
        executePendingBindings()
      }
    }
  }
}

private class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

  override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
    return oldItem == newItem
  }
}