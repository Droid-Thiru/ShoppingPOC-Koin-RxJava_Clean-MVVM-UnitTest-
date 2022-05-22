package com.example.lillypoc.presenters.storeproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.domain.model.StoreProducts
import com.example.lillypoc.domain.model.Storeinfo
import com.example.lillypoc.domain.usecase.StoreProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class StoreProductsViewModelTest {
    lateinit var viewModel: StoreProductsViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockStoreProductsUseCase: StoreProductsUseCase


    private val product = Product(0, "", "", 0, "")
    private val productList = ArrayList<Product>()
    private val storeInfo = Storeinfo("", "", "", "")
    private val storeProducts = StoreProducts(productList, storeInfo)

    private val mockErrorMsg = "Error Message"

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = StoreProductsViewModel(mockStoreProductsUseCase)

        productList.add(product)
    }

    @Test
    fun `verify getStoreInfoAndProducts success`() {
        coEvery { mockStoreProductsUseCase.getStoreInfoAndProducts() } returns Single.just(
            storeProducts
        )
        viewModel.getStoreInfoAndProducts()
        assertTrue(!viewModel.progressBarLiveData.value!!)
        assertEquals(storeInfo, viewModel.storeInfoLiveData.value)
        assertEquals(productList, viewModel.productsLiveData.value)
    }

    @Test
    fun `verify getStoreInfoAndProducts Error`() {
        coEvery { mockStoreProductsUseCase.getStoreInfoAndProducts() } returns Single.error(
            Throwable(mockErrorMsg)
        )
        viewModel.getStoreInfoAndProducts()
        assertEquals(false, viewModel.progressBarLiveData.value)
        assertEquals(mockErrorMsg, viewModel.errorMsgLiveData.value)
    }

    /* @Test
     fun `verify getUserSelectedProducts Error`(){
         //val pair = Pair(productList,1000)
         coEvery { mockStoreProductsUseCase.getUserSelectedProductsFromList(mockSelectedListItem,productList) } returns Observable.error(Throwable(mockErrorMsg))
         viewModel.getUserSelectedProducts(mockSelectedListItem)
         assertEquals(mockErrorMsg,viewModel.errorMsgLiveData.value)
     }*/
}