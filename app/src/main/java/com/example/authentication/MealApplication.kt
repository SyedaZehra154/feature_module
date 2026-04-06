package com.example.authentication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// we inject it here so viewmodel repo can automatically injected without manually creating them
@HiltAndroidApp
class MealApplication : Application()