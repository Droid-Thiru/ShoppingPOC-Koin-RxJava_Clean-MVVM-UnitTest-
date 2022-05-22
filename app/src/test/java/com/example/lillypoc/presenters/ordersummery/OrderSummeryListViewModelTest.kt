package com.example.lillypoc.presenters.ordersummery

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lillypoc.base.Constants.SELECTED_PRODUCTS
import com.example.lillypoc.domain.model.Product
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class OrderSummeryListViewModelTest {
    lateinit var viewModel: OrderSummeryListViewModel

    @MockK
    lateinit var mockBundle: Bundle

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val product = Product(0, "", "", 0, "")
    private val productList = ArrayList<Product>()

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        viewModel = OrderSummeryListViewModel()

    }

    @Test
    fun `verify onCreate`(){
       coEvery { mockBundle[SELECTED_PRODUCTS] } returns productList
        viewModel.onCreate(mockBundle)
        assertEquals(productList,viewModel.selectedProducts)
    }
}