package com.example.lillypoc.presenters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lillypoc.R
import com.example.lillypoc.databinding.OrderSummeryListRowItemBinding
import com.example.lillypoc.domain.model.Product

class OrderSummeryListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<OrderSummeryListAdapter.OrderSummeryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSummeryViewHolder {
        val binding = OrderSummeryListRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderSummeryViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: OrderSummeryViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class OrderSummeryViewHolder(
        private val context: Context,
        private val binding: OrderSummeryListRowItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * load selected product in the list one by one
         */
        fun bind(product: Product) {
            binding.run {
                product.run {
                    Glide.with(context)
                        .load(image)
                        .into(orderSummeryListProductImage)
                    orderSummeryListProductNameTxt.text = name
                    orderSummeryListQtyTxt.text = context.getString(R.string.list_quantity, userQty)
                    orderSummeryListPriceTxt.text = context.getString(R.string.list_price, price)
                    orderSummeryListTotalPriceTxt.text =
                        context.getString(R.string.list_total_price, getUserTotalPrice())
                }
            }
        }
    }

}