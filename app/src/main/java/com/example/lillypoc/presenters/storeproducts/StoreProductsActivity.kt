package com.example.lillypoc.presenters.storeproducts

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.lillypoc.R
import com.example.lillypoc.base.BaseActivity
import com.example.lillypoc.databinding.ActivityStoreProductsBinding
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.presenters.adapter.ProductListAdapter
import com.example.lillypoc.presenters.ordersummery.OrderSummeryActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class StoreProductsActivity : BaseActivity() {
    private lateinit var binding: ActivityStoreProductsBinding
    private val viewModel: StoreProductsViewModel by inject()

    private var adapter: ProductListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_store_products
        )
        //setContentView(binding.root)

        setStoreInfo()
        setProductListData()
        setErrorListener()
        selectedProducts()
        viewModel.getStoreInfoAndProducts()

        viewModel.progressBarLiveData.observe(this) {
            if (it) {
                showProgress()
            } else {
                dismissProgress()
            }
        }

        /**
         * Order summer action event
         */
        binding.orderSummeryBtn.setOnClickListener {
            adapter?.let {
                viewModel.getUserSelectedProducts(it.getSelectedItemPosition())
            }
        }
    }

    /**
     * Display Store info in top of the screen
     * like [Logo] and binding the data to the layout xml
     */
    private fun setStoreInfo() {
        viewModel.storeInfoLiveData.observe(this) {
            binding.storeInfo = it
            Glide.with(this)
                .load(it.logo)
                .into(binding.storeImage)
        }
    }

    /**
     * set Product list into the list adapter
     */
    private fun setProductListData() {
        viewModel.productsLiveData.observe(this) {
            adapter = ProductListAdapter(it)
            binding.adapter = adapter
        }
    }

    /**
     * show Error messages in bottom of the screen
     */
    private fun setErrorListener() {
        viewModel.errorMsgLiveData.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Selected product call back
     */
    private fun selectedProducts() {
        viewModel.selectedProductsLiveData.observe(this) { pair ->
            OrderSummeryActivity.start(this, pair.second, pair.first as ArrayList<Product>)
        }
    }
}