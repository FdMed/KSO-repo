package com.fd.kso.data.repositories

import com.fd.kso.data.api.ApiService
import com.fd.kso.data.model.MyItem
import com.fd.kso.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepository @Inject constructor(private val apiService: ApiService) {


    val allItems = flow<Resource<List<MyItem>>> {
        try {
            val response = apiService.getListOfItems()
            emit(Resource.success(response))
        } catch (e: Exception) {
            e.message?.let { emit(Resource.error(null, it)) }
        }
    }
}