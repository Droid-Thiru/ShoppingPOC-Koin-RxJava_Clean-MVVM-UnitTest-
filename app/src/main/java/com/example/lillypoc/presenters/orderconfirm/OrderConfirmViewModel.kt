package com.example.lillypoc.presenters.orderconfirm

import com.example.lillypoc.base.BaseViewModel
import com.example.lillypoc.base.Constants.SELECTED_PRODUCTS
import com.example.lillypoc.common.Preference
import com.example.lillypoc.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OrderConfirmViewModel : BaseViewModel() {

    /**
     * get confirmed order list form local shared preference
     */
    fun getConfirmedOrderFromPref(localJson:String): List<Product>? {
        return if (localJson.isEmpty()) {
            null
        } else {
            Gson().fromJson(localJson, object : TypeToken<List<Product>>() {}.type)
        }
    }
}