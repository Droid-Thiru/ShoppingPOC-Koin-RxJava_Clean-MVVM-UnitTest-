package com.example.lillypoc.data.remote

import com.example.lillypoc.domain.model.StoreProducts
import io.reactivex.Single
import retrofit2.http.GET

interface StoreApi {
    /**
     * initialize the store info API
     */
    @GET("40561851-206f-4c6e-b2f1-034997395562")
    fun getStoreProducts():Single<StoreProducts>
}