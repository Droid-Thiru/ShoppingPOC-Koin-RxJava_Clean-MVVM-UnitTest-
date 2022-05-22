package com.example.lillypoc.domain.repository

import com.example.lillypoc.domain.model.StoreProducts
import io.reactivex.Single

interface StoreProductsRepository {
    fun getStoreProducts(): Single<StoreProducts>
}