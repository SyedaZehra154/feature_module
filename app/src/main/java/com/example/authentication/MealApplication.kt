package com.example.authentication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// we inject it here so viewmodel repo can automatically injected without manually creating them
@HiltAndroidApp
class MealApplication : Application()

//We use the Application class with @HiltAndroidApp to initialize Hilt and create the dependency graph
// at the app level so that dependencies can be injected throughout the application.