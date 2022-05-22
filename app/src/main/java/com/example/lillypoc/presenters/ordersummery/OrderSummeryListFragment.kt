package com.example.lillypoc.presenters.ordersummery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lillypoc.base.Constants
import com.example.lillypoc.databinding.FragmentOrderSummeryListBinding
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.presenters.adapter.OrderSummeryListAdapter
import org.koin.android.ext.android.inject

class OrderSummeryListFragment : Fragment() {

    private val viewModel: OrderSummeryListViewModel by inject()
    private lateinit var binding: FragmentOrderSummeryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSummeryListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedProducts?.let {
            binding.adapter = OrderSummeryListAdapter(it)
        }
    }

    companion object {
        /**
         * create instant for OrderSummeryListFragment with [selectedProducts]
         */
        fun newInstant(selectedProducts: List<Product>): OrderSummeryListFragment {
            val fragment = OrderSummeryListFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(
                Constants.SELECTED_PRODUCTS,
                selectedProducts as ArrayList<Product>
            )
            fragment.arguments = bundle
            return fragment
        }
    }
}