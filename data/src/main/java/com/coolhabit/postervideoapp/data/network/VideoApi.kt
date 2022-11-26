package com.coolhabit.postervideoapp.data.network

import com.coolhabit.postervideoapp.data.network.entities.NetworkResponse
import retrofit2.http.GET

interface VideoApi {

    @GET("api/backgrounds/?group=video&category_id=1")
    suspend fun getVideoItems(): NetworkResponse
}
