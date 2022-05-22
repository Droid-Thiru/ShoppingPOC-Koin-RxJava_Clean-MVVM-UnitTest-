package com.example.lillypoc.data.repository

import com.example.lillypoc.data.remote.StoreApi
import com.example.lillypoc.domain.model.Product
import com.example.lillypoc.domain.model.StoreProducts
import com.example.lillypoc.domain.model.Storeinfo
import com.example.lillypoc.domain.repository.StoreProductsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StoreProductsRepositoryImplTest {
    lateinit var mockRepositoryImpl: StoreProductsRepositoryImpl


    @MockK
    lateinit var mockStoreApi: StoreApi

    private val productList = ArrayList<Product>()
    private val storeInfo = Storeinfo("", "", "", "")
    private val storeProducts = StoreProducts(productList, storeInfo)

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mockRepositoryImpl = StoreProductsRepositoryImpl(mockStoreApi)
    }

    @Test
    fun `verify getStoreProducts`(){
        val input = Single.just(storeProducts)
        coEvery { mockStoreApi.getStoreProducts() } returns input
        val result = mockRepositoryImpl.getStoreProducts()
        assertEquals(input,result)
    }
}