package com.example.lillypoc.presenters.ordersummery

import android.os.Bundle
import com.example.lillypoc.base.BaseViewModel
import com.example.lillypoc.base.Constants
import com.example.lillypoc.domain.model.Product

class OrderSummeryListViewModel : BaseViewModel() {
    var selectedProducts: ArrayList<Product>? = null
    fun onCreate(bundle: Bundle?) {
        bundle?.let {
            /**
             * get ordered list from fragment arguments
             */
            selectedProducts = it[Constants.SELECTED_PRODUCTS] as ArrayList<Product>
        }
    }
}