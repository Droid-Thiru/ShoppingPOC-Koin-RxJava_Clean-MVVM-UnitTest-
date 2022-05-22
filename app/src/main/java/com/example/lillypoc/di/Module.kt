package com.example.lillypoc.di


import com.example.lillypoc.base.Constants
import com.example.lillypoc.data.remote.StoreApi
import com.example.lillypoc.data.repository.StoreProductsRepositoryImpl
import com.example.lillypoc.domain.repository.StoreProductsRepository
import com.example.lillypoc.domain.usecase.StoreProductsUseCase
import com.example.lillypoc.presenters.orderconfirm.OrderConfirmViewModel
import com.example.lillypoc.presenters.ordersummery.OrderSummeryListViewModel
import com.example.lillypoc.presenters.ordersummery.OrderSummeryViewModel
import com.example.lillypoc.presenters.storeproducts.StoreProductsViewModel
import com.example.network.NetworkManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule)
    )
}

/**
 * create network koin Module
 */
val networkModule = module {
    single { NetworkManager() }
    single { get<NetworkManager>().getClient(Constants.BASE_URL)}
    single { get<Retrofit>().create(StoreApi::class.java) }
}

/**
 * create repository koin module
 */
val repositoryModule = module {
    factory<StoreProductsRepository> { StoreProductsRepositoryImpl(get()) }
}

/**
 * create use case koin module
 */
val useCaseModule = module {
    factory { StoreProductsUseCase(get()) }
}

/**
 * create view model koin module
 */
val viewModelModule = module{
    viewModel { StoreProductsViewModel(get()) }
    viewModel { OrderSummeryViewModel() }
    viewModel { OrderSummeryListViewModel() }
    viewModel { OrderConfirmViewModel() }

}