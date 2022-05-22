package com.example.lillypoc.presenters.storeproducts

import android.util.SparseBooleanArray
import androidx.core.util.forEach
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lillypoc.base.BaseViewModel
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.domain.model.Storeinfo
import com.example.lillypoc.domain.usecase.StoreProductsUseCase
import io.reactivex.Observable

class StoreProductsViewModel(private val useCase: StoreProductsUseCase) : BaseViewModel() {

    private val storeInfoMutableLiveData by lazy { MutableLiveData<Storeinfo>() }
    val storeInfoLiveData: LiveData<Storeinfo> by lazy { storeInfoMutableLiveData }

    private val productsMutableLiveData by lazy { MutableLiveData<List<Product>>() }
    val productsLiveData: LiveData<List<Product>> by lazy { productsMutableLiveData }

    private val errorMsgMutableLiveData by lazy { MutableLiveData<String>() }
    val errorMsgLiveData: LiveData<String> by lazy { errorMsgMutableLiveData }

    private val selectedProductsMutableLiveData by lazy { MutableLiveData<Pair<List<Product>,Int>>() }
    val selectedProductsLiveData: LiveData<Pair<List<Product>,Int>> by lazy { selectedProductsMutableLiveData }

    private var productList: List<Product>? = null

    /**
     * get products with storeInfo from API
     */
    fun getStoreInfoAndProducts() {
        progressBarMutableLiveData.value = true
        compositeDisposable.add(
            useCase.getStoreInfoAndProducts()
                .subscribe({
                    progressBarMutableLiveData.value = false
                    storeInfoMutableLiveData.value = it.storeinfo
                    productList = it.products
                    productsMutableLiveData.value = productList
                }, {
                    progressBarMutableLiveData.value = false
                    errorMsgMutableLiveData.value = it.message
                })
        )
    }

    /**
     * return TotalPrice and selected products info from list using [selectedItems]
     */
    fun getUserSelectedProducts(selectedItems: SparseBooleanArray) {
        progressBarMutableLiveData.value = true
        compositeDisposable.add(useCase.getUserSelectedProductsFromList(
            selectedItems, productList
        ).subscribe({
            if(it.first.isEmpty()){
                errorMsgMutableLiveData.value = "Please select minimum one product from the list."
            }else {
                selectedProductsMutableLiveData.value = it
            }
            progressBarMutableLiveData.value = false
        }, {
            errorMsgMutableLiveData.value = it.message
            progressBarMutableLiveData.value = false
        })
        )
    }
}