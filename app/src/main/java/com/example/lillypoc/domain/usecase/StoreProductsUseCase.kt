package com.example.lillypoc.domain.usecase

import android.util.SparseBooleanArray
import androidx.core.util.forEach
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.domain.model.StoreProducts
import com.example.lillypoc.domain.repository.StoreProductsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StoreProductsUseCase(private val repository: StoreProductsRepository) {

    fun getStoreInfoAndProducts(): Single<StoreProducts> {
        return repository.getStoreProducts()
            .onErrorResumeNext {
                return@onErrorResumeNext Single.error(Throwable("something went wrong."))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * filter the selected item from product list and calculate the total price
     */
    fun getUserSelectedProductsFromList(selectedItems: SparseBooleanArray, products: List<Product>?)
            : Observable<Pair<List<Product>,Int>> {
        val selectedProducts = mutableListOf<Product>()
        var totalPrice = 0
        return Observable.just(selectedItems).flatMap {
            it.forEach { key, _ ->
                products?.let { list ->
                    selectedProducts.add(list[key])
                    totalPrice += list[key].getUserTotalPrice()
                }
            }
            Observable.just(Pair(selectedProducts,totalPrice))
        }
    }
}