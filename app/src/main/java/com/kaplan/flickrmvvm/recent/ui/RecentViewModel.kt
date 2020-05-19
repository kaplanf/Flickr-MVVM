package com.kaplan.flickrmvvm.recent.ui

import androidx.lifecycle.ViewModel
import com.kaplan.flickrmvvm.di.CoroutineScropeIO
import com.kaplan.flickrmvvm.recent.data.PhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject


class RecentViewModel @Inject constructor(private val repository: PhotosRepository,
                                            @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : ViewModel() {

    var connectivityAvailable: Boolean = false

    val photos by lazy {
        repository.observePagedSets(
                connectivityAvailable, ioCoroutineScope)
    }

    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
