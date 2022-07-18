package com.example.jatlin.di

import com.example.jatlin.data.PreferenceManager
import com.example.jatlin.data.db.provideDB
import com.example.jatlin.data.db.provideToDoDao
import com.example.jatlin.data.repository.DefaultFirebaseRepository
import com.example.jatlin.data.repository.FirebaseRepository
import com.example.jatlin.domain.GetCurrentUserUseCase
import com.example.jatlin.domain.InsertUserUseCase
import com.example.jatlin.presentation.chat.ChatContract
import com.example.jatlin.presentation.chat.ChatFragment
import com.example.jatlin.presentation.chat.ChatPresenter
import com.example.jatlin.presentation.chat.detail.ChatDetailContract
import com.example.jatlin.presentation.chat.detail.ChatDetailFragment
import com.example.jatlin.presentation.chat.detail.ChatDetailPresenter
import com.example.jatlin.presentation.login.LoginContract
import com.example.jatlin.presentation.login.LoginFragment
import com.example.jatlin.presentation.login.LoginPresenter
import com.example.jatlin.presentation.product.ProductContract
import com.example.jatlin.presentation.product.ProductFragment
import com.example.jatlin.presentation.product.ProductPresenter
import com.example.jatlin.presentation.product.create.ProductCreateContract
import com.example.jatlin.presentation.product.create.ProductCreateFragment
import com.example.jatlin.presentation.product.create.ProductCreatePresenter
import com.example.jatlin.presentation.product.detail.ProductDetailContract
import com.example.jatlin.presentation.product.detail.ProductDetailFragment
import com.example.jatlin.presentation.product.detail.ProductDetailPresenter
import com.example.jatlin.presentation.profile.ProfileContract
import com.example.jatlin.presentation.profile.ProfileFragment
import com.example.jatlin.presentation.profile.ProfilePresenter
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // Coroutine Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    single { PreferenceManager(androidContext()) }
}

val useCaseModule = module{
    factory { GetCurrentUserUseCase(get()) }
    factory { InsertUserUseCase(get()) }
}

val databaseModule = module{
    // Repository
    single<FirebaseRepository> { DefaultFirebaseRepository(get(), get()) }

    // DataBase
    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }
}

val presenterModule = module {

    // 프레젠터
    // 스코프내에서는 얼마든지 공유가 가능하다.
    scope<ProfileFragment> {
        scoped<ProfileContract.Presenter> { ProfilePresenter(get()) }
    }
    scope<LoginFragment> {
        scoped<LoginContract.Presenter> { LoginPresenter(get(),get()) }
    }
    scope<ProductFragment> {
        scoped<ProductContract.Presenter> { ProductPresenter(get()) }
    }
    scope<ProductCreateFragment> {
        scoped<ProductCreateContract.Presenter> { ProductCreatePresenter(get()) }
    }
    scope<ProductDetailFragment> {
        scoped<ProductDetailContract.Presenter> { ProductDetailPresenter(get()) }
    }
    scope<ChatFragment> {
        scoped<ChatContract.Presenter> { ChatPresenter(get()) }
    }
    scope<ChatDetailFragment> {
        scoped<ChatDetailContract.Presenter> { ChatDetailPresenter(get()) }
    }


}