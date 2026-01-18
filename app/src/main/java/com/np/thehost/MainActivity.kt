package com.np.thehost

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.np.thehost.ui.theme.THEHOSTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            THEHOSTTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("profile") { ProfileListScreen(navController) }
                        composable("scanner") { PlaceholderScreen("Scanner", navController) }
                        composable("records") { PlaceholderScreen("Records", navController) }
                        composable("settings") { PlaceholderScreen("Settings", navController) }
                        composable(
                            route = "bio/{userId}"
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: return@composable
                            BioScreen(navController, userId)
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "THE DREAM TEAM APP",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        val menuItems = listOf("Profile", "Scanner", "Records", "Settings")
        menuItems.forEach { label ->
            Button(
                onClick = { navController.navigate(label.lowercase()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(label)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { context?.finishAffinity() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Logout & Exit", color = Color.White)
        }
    }
}
