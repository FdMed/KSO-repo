package com.fd.kso.data.api

import com.fd.kso.data.model.MyItem
import retrofit2.http.GET

interface ApiService {

    @GET("e7c59880-e25a-4fb1-82cd-9ba6107bcb8d/")
    suspend fun getListOfItems(): List<MyItem>
}