package com.kaplan.flickrmvvm.recent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kaplan.flickrmvvm.R
import com.kaplan.flickrmvvm.binding.bindImageFromUrl
import com.kaplan.flickrmvvm.databinding.FragmentDetailBinding
import com.kaplan.flickrmvvm.di.Injectable
import com.kaplan.flickrmvvm.recent.data.Photo
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var photo: Photo


  private val args: DetailFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
      inflater, R.layout.fragment_detail, container, false).apply {
      lifecycleOwner = this@DetailFragment
    }

    args?.let {
      bindImageFromUrl(binding.detailImage, args.url)
    }
    return binding.root
  }
}
