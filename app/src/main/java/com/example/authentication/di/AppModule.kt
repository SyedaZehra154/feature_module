//package com.example.authentication.di
//
//import androidx.room.Room
//import com.example.authentication.data.local.database.UserDatabase
//import com.example.authentication.data.repository.UserRepositoryImpl
//import com.example.authentication.domain.repository.UserRepository
//import com.example.authentication.presentation.dashboard.DashboardViewModel
//import com.example.authentication.presentation.login.LoginViewModel
//import com.example.authentication.presentation.signup.SignupViewModel
//import com.google.firebase.auth.FirebaseAuth
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.module.dsl.viewModel
//import org.koin.dsl.module
//
//val appModule= module {
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            UserDatabase::class.java,
//            "user_db"
//        ).build()
//    }
//    single {
//        get<UserDatabase>().userDao()
//    }
//    single {
//        FirebaseAuth.getInstance()
//    }
//
//    single<UserRepository> {
//        UserRepositoryImpl(
//            fireBaseAuth =get(),
//            userDao=get()
//        )
//    }
//
//    viewModel { LoginViewModel(get()) }
//
//    viewModel { SignupViewModel(get()) }
//
//    viewModel { DashboardViewModel(get()) }
//}