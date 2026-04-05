//package com.example.authentication.presentation.dashboard
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.authentication.domain.repository.UserRepository
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.stateIn
//
//class DashboardViewModel (
//    userRepository: UserRepository
//): ViewModel(){
//
//    val state = userRepository.getUser()
//        .map { user ->
//
//            DashboardState(
//                name = user?.name ?: "",
//                email = user?.email ?: ""
//            )
//
//        }.stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            DashboardState()
//        )
//
//}