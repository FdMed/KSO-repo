package com.fd.kso.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fd.kso.data.api.ApiService
import com.fd.kso.data.repositories.LocationRepository
import com.fd.kso.ui.main.MainViewmodel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewmodel::class.java)) {
            return MainViewmodel(LocationRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")    }

}