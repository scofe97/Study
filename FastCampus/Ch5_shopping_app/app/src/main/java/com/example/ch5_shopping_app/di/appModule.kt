package com.example.ch5_shopping_app.di

import com.example.ch5_shopping_app.data.db.provideDB
import com.example.ch5_shopping_app.data.db.provideToDoDao
import com.example.ch5_shopping_app.data.network.buildOkHttpClient
import com.example.ch5_shopping_app.data.network.provideGsonConverterFactory
import com.example.ch5_shopping_app.data.network.provideProductApiService
import com.example.ch5_shopping_app.data.network.provideProductRetrofit
import com.example.ch5_shopping_app.data.preference.PreferenceManager
import com.example.ch5_shopping_app.data.repository.DefaultProductRepository
import com.example.ch5_shopping_app.data.repository.ProductRepository
import com.example.ch5_shopping_app.domain.*
import com.example.ch5_shopping_app.domain.DeleteOrderedProductListUseCase
import com.example.ch5_shopping_app.domain.GetOrderedProductListUseCase
import com.example.ch5_shopping_app.domain.GetProductItemUseCase
import com.example.ch5_shopping_app.domain.GetProductListUseCase
import com.example.ch5_shopping_app.domain.OrderProductItemUseCase
import com.example.ch5_shopping_app.presentation.detail.ProductDetailViewModel
import com.example.ch5_shopping_app.presentation.list.ProductListViewModel
import com.example.ch5_shopping_app.presentation.main.MainViewModel
import com.example.ch5_shopping_app.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    // Coroutine Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // ViewModel
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel( get() ) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { (productId : Long) -> ProductDetailViewModel(productId, get(), get() ) }

    // UseCase
    factory { GetProductItemUseCase(get() ) }
    factory { GetProductListUseCase(get() ) }
    factory { OrderProductItemUseCase(get() ) }
    factory { GetOrderedProductListUseCase(get() ) }
    factory { DeleteOrderedProductListUseCase(get() ) }

    // Repository
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }

    single { provideGsonConverterFactory()}

    single { buildOkHttpClient()}

    single { provideProductRetrofit(get(),get())}

    single { provideProductApiService(get())}

    single { PreferenceManager(androidContext()) }

    // DataBase
    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

}