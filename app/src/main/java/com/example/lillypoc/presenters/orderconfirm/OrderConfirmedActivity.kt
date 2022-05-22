package com.example.lillypoc.presenters.orderconfirm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.lillypoc.base.BaseActivity
import com.example.lillypoc.base.Constants
import com.example.lillypoc.common.Preference
import com.example.lillypoc.databinding.ActivityOrderConfirmedBinding
import com.example.lillypoc.presenters.ordersummery.OrderSummeryListFragment
import org.koin.android.ext.android.inject

class OrderConfirmedActivity : BaseActivity() {

    private lateinit var binding: ActivityOrderConfirmedBinding
    private val viewModel: OrderConfirmViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         * Order confirm done action
         */
        binding.orderConfirmDoneBtn.setOnClickListener {
            finish()
        }

        /**
         * Replace the reusable ordered list fragment in to the container
         */
        supportFragmentManager.beginTransaction().run {
            val orderJson: String = Preference.getValue(Constants.SELECTED_PRODUCTS, "") as String
            viewModel.getConfirmedOrderFromPref(orderJson)?.let {
                replace(
                    binding.orderConfirmSuccessListContainer.id,
                    OrderSummeryListFragment.newInstant(it)
                )
                commit()
            }

        }
    }

    companion object {
        /**
         * start the OrderConfirmedActivity
         */
        fun start(context: Context) {
            context.startActivity(Intent(context, OrderConfirmedActivity::class.java))
        }
    }
}