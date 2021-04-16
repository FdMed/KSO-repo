package com.fd.kso.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fd.kso.data.model.MyItem
import com.fd.kso.data.repositories.LocationRepository
import com.fd.kso.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainViewmodel @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {

    val allItems = liveData <Resource<List<MyItem>>> {
        locationRepository.allItems
                .onStart { emit(Resource.loading(null)) }
                .catch { exception -> emit(Resource.error(null,message = exception.message ?: "An error occured")) }
                .collect { emit(it) }
    }
}