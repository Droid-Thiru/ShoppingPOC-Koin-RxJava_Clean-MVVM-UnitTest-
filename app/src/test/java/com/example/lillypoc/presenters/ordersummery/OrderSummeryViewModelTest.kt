package com.example.lillypoc.presenters.ordersummery

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lillypoc.base.Constants
import com.example.lillypoc.domain.model.Product
import com.google.gson.Gson
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
class OrderSummeryViewModelTest {
    lateinit var viewModel: OrderSummeryViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockBundle: Bundle

    private val product = Product(0, "", "", 0, "")
    private val productList = ArrayList<Product>()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        viewModel = OrderSummeryViewModel()
        productList.add((product))
    }

    @Test
    fun `verify onCreate`(){
        coEvery { mockBundle.getInt(Constants.TOTAL_PRICE,0) } returns 10
        coEvery { mockBundle[Constants.SELECTED_PRODUCTS] } returns productList

        viewModel.onCreate(mockBundle)
        assertEquals(10,viewModel.totalPrice)
        assertEquals(productList,viewModel.selectedProducts)

    }

    @Test
    fun `verify deliveryAddressValidation is valid`(){
        viewModel.deliveryAddress = "Test"
        viewModel.deliveryAddressValidation()
        assertEquals(true,viewModel.addressValidationLiveData.value?.second)
    }

    @Test
    fun `verify deliveryAddressValidation is inValid`(){
        viewModel.deliveryAddress = ""
        viewModel.deliveryAddressValidation()
        assertEquals(false,viewModel.addressValidationLiveData.value?.second)
    }

    @Test
    fun `verify createJsonFromList with list is not empty`(){
        val json = Gson().toJson(productList)
        viewModel.selectedProducts = productList
        val result = viewModel.createJsonFromList()
        assertEquals(json, result)
    }

    @Test
    fun `verify createJsonFromList with list is empty`(){
        viewModel.selectedProducts = null
        val result = viewModel.createJsonFromList()
        assertEquals(null, result)
    }
}