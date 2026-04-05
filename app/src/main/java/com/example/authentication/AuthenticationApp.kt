//package com.example.authentication
//
//import android.app.Application
//import android.util.Log
//import com.example.authentication.di.appModule
//import com.fusion.twofa.core.Fusion2FA
//import com.google.firebase.FirebaseApp
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin
//
//
//class AuthenticationApp : Application() {
//    override fun onCreate() {
//        super.onCreate()
//        Log.d("AuthenticationApp", "onCreate: Initializing Fusion2FA and Firebase")
//        Fusion2FA.init(this)
//        FirebaseApp.initializeApp(this)
//
//
//        startKoin {
//            androidLogger()
//            androidContext(this@AuthenticationApp)
//            modules(appModule)
//        }
//    }
//}
