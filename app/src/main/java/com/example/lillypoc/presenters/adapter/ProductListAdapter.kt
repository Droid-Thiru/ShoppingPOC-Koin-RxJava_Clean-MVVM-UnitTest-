package com.example.lillypoc.presenters.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lillypoc.R
import com.example.lillypoc.base.gone
import com.example.lillypoc.base.visible
import com.example.lillypoc.databinding.ProductListRowItemBinding
import com.example.lillypoc.domain.model.Product


class ProductListAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ListViewHolder>() {

    private val selectedItems by lazy { SparseBooleanArray() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ProductListRowItemBinding = ProductListRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ListViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ListViewHolder(
        private val context: Context,
        private val binding: ProductListRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Load the product item in to the list one by one
         */
        fun bind(product: Product) {
            binding.run {
                product.run {
                    addBtn.tag = availableQty
                    reduceBtn.tag = availableQty
                    Glide.with(context)
                        .load(image)
                        .into(productImg)
                    productNameTxt.text = name
                    productPriceTxt.text = context.getString(R.string.product_list_price, price)
                }
                itemView.setOnClickListener {
                    qtyLayout.visible()
                }

                addBtn.setOnClickListener {
                    run {
                        var qty = qtyTxt.text.toString().toInt()
                        qtyTxt.text = "${++qty}"
                        addBtn.isEnabled = qty < it.tag.toString().toInt()
                        reduceBtn.isEnabled = true
                        selectedItems.put(adapterPosition, true)
                        productList[adapterPosition].userQty = qty
                        productSelectCb.visible()
                        productSelectCb.isChecked = true
                    }
                }

                reduceBtn.setOnClickListener {
                    run {
                        var qty = qtyTxt.text.toString().toInt()
                        qtyTxt.text = "${--qty}"
                        reduceBtn.isEnabled = qty > 0
                        addBtn.isEnabled = true
                        if (qty == 0) {
                            selectedItems.delete(adapterPosition)
                            productSelectCb.gone()
                            productSelectCb.isChecked = false
                        }
                        productList[adapterPosition].userQty = qty
                    }
                }
            }
        }

    }

    fun getSelectedItemPosition(): SparseBooleanArray {
        return selectedItems
    }

}