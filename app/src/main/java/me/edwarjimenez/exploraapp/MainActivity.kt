package me.edwarjimenez.exploraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.edwarjimenez.exploraapp.ui.theme.ExploraAppTheme
import me.edwarjimenez.exploraapp.ui.theme.LoginScreen
import me.edwarjimenez.exploraapp.ui.theme.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myNavController = rememberNavController()

            NavHost(
                navController = myNavController,
                startDestination = "login",
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = "login"){
                    LoginScreen(onLoginSuccess = {}, onNavigateToRegister = {
                        myNavController.navigate("register")
                    })
                }

                composable("register") {
                    RegisterScreen(
                        onRegisterSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            myNavController.popBackStack()
                        },
                        onBackClick = {
                            myNavController.popBackStack() // ← Esto es lo que faltaba
                        }
                    )
                }
            }

        }

    }
}