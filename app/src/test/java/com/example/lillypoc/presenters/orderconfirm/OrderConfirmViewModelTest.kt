package com.example.lillypoc.presenters.orderconfirm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lillypoc.domain.model.Product
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class OrderConfirmViewModelTest {
    lateinit var viewModel: OrderConfirmViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val product = Product(0, "", "", 0, "")
    private val productList = ArrayList<Product>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = OrderConfirmViewModel()
        productList.add(product)
    }

    @Test
    fun `verify getConfirmedOrderFromPref`() {
        val json = Gson().toJson(productList)
        val result = viewModel.getConfirmedOrderFromPref(json)
        assertEquals(productList, result)
    }

    @Test
    fun `verify getConfirmedOrderFromPref with empty json`() {
        val json = ""
        val result = viewModel.getConfirmedOrderFromPref(json)
        assertEquals(null, result)
    }
}