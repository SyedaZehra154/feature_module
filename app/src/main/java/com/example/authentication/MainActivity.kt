package com.example.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.authentication.navigation.SetupNavGraph
import com.example.feature_auth.presentation.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = hiltViewModel() // Inject ViewModel

                    // Pass authViewModel to NavGraph
                    SetupNavGraph(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}

/*
package com.example.authentication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.authentication.presentation.navigation.AppNavGraph
import com.example.authentication.ui.theme.AuthenticationTheme
import com.fusion.twofa.core.Fusion2FA
import com.google.firebase.messaging.FirebaseMessaging
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
            Log.d("MainActivity", "Firebase token received: $token")
            Fusion2FA.setFirebaseToken(token.orEmpty())
            enableEdgeToEdge()
            setContent {
                AuthenticationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                      ) {

                    }
                }
                AppNavGraph()
            }
        }.addOnFailureListener {
            Log.e("MainActivity", "Failed to get Firebase token", it)
        }
    }
}
*/
