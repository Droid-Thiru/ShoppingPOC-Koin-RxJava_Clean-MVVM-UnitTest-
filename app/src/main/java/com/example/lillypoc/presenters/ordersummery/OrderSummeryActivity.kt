package com.example.lillypoc.presenters.ordersummery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.lillypoc.R
import com.example.lillypoc.base.BaseActivity
import com.example.lillypoc.base.Constants.SELECTED_PRODUCTS
import com.example.lillypoc.base.Constants.TOTAL_PRICE
import com.example.lillypoc.common.Preference
import com.example.lillypoc.databinding.ActivityOrderSummeryBinding
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.presenters.orderconfirm.OrderConfirmedActivity
import org.koin.android.ext.android.inject


class OrderSummeryActivity : BaseActivity() {
    private lateinit var binding: ActivityOrderSummeryBinding
    private val viewModel: OrderSummeryViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_order_summery
        )
        setContentView(binding.root)
        viewModel.onCreate(intent?.extras)

        binding.viewModel = viewModel
        setSelectedOrderList()
        setDeliveryAddressValidationCallBack()

        /**
         * Order Confirm action
         */
        binding.orderSummeryOrderBtn.setOnClickListener {
            viewModel.deliveryAddressValidation()
        }
    }

    /**
     * replace the selected order fragment in to the container
     */
    private fun setSelectedOrderList() {
        viewModel.selectedProducts?.let {
            supportFragmentManager.beginTransaction().run {
                replace(
                    binding.orderListContainer.id,
                    OrderSummeryListFragment.newInstant(it),
                    "OrderList"
                )
                commit()
            }

        }
    }

    private fun setDeliveryAddressValidationCallBack() {
        viewModel.addressValidationLiveData.observe(this) {
            if (it.second) {
                /**
                 * Store confirm order in to the local preference
                 */
                Preference.addPref(SELECTED_PRODUCTS, viewModel.createJsonFromList()?:"")
                finish()
                OrderConfirmedActivity.start(this)

            }
            binding.addressLayout.error = it.first
            binding.addressLayout.isErrorEnabled = !it.second
        }
    }

    companion object {
        /**
         * start the OrderSummeryActivity with [totalPrice] and [selectedProducts]
         */
        fun start(context: Context, totalPrice: Int, selectedProducts: ArrayList<Product>) {
            val intent = Intent(context, OrderSummeryActivity::class.java)
            val bundle = Bundle()
            bundle.putInt(TOTAL_PRICE, totalPrice)
            bundle.putParcelableArrayList(SELECTED_PRODUCTS, selectedProducts)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}