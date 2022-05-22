package com.example.lillypoc.data.repository

import com.example.lillypoc.data.remote.StoreApi
import com.example.lillypoc.domain.model.StoreProducts
import com.example.lillypoc.domain.repository.StoreProductsRepository
import io.reactivex.Single

class StoreProductsRepositoryImpl(private val storeApi: StoreApi): StoreProductsRepository {
    override fun getStoreProducts(): Single<StoreProducts> {
        return storeApi.getStoreProducts()
    }
}