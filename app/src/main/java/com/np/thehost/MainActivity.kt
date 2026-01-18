package com.np.thehost

import android.annotation.SuppressLint
import android.app.Activity // Required for finishAffinity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext // Required to get Activity context
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.np.thehost.ui.theme.THEHOSTTheme

// --- DATA MODEL ---
data class UserProfile(val id: Int, val name: String, val role: String, val imageRes: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            THEHOSTTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("profile") { ProfileListScreen(navController) }
                        composable("bio") { BioScreen(navController) }
                        composable("scanner") { PlaceholderScreen("Scanner", navController) }
                        composable("records") { PlaceholderScreen("Records", navController) }
                        composable("settings") { PlaceholderScreen("Settings", navController) }
                    }
                }
            }
        }
    }
}

// --- SCREEN 1: HOME (With Logout) ---
@SuppressLint("ContextCastToActivity")
@Composable
fun HomeScreen(navController: NavHostController) {
    // Access the current Activity context to close the app
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
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Navigation Buttons
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

        // --- LOGOUT / TERMINATE BUTTON ---
        Button(
            onClick = {
                // This closes all activities and terminates the app process
                context?.finishAffinity()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error // Red color for exit
            )
        ) {
            Text("Logout & Exit", color = Color.White)
        }
    }
}

// --- SCREEN 2: PROFILE LIST ---
@Composable
fun ProfileListScreen(navController: NavHostController) {
    val teamMembers = listOf(
        UserProfile(1, "Juan Dela Cruz", "Lead Developer", R.drawable.ic_launcher_foreground),
        UserProfile(2, "Maria Santos", "UI/UX Designer", R.drawable.ic_launcher_foreground),
        UserProfile(3, "Pedro Penduko", "Data Analyst", R.drawable.ic_launcher_foreground),
        UserProfile(4, "Elena Adarna", "Project Manager", R.drawable.ic_launcher_foreground),
        UserProfile(5, "Jose Rizal", "System Architect", R.drawable.ic_launcher_foreground)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("The Dream Team", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(teamMembers) { member ->
                ProfileCard(member) { navController.navigate("bio") }
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Back to Home")
        }
    }
}

// --- PROFILE CARD COMPONENT ---
@Composable
fun ProfileCard(user: UserProfile, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = user.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = user.name, fontWeight = FontWeight.Bold)
                Text(text = user.role, color = Color.Gray, fontSize = 14.sp)
            }
            Button(onClick = onClick) { Text("Bio") }
        }
    }
}

// --- SCREEN 3: BIO ---
@Composable
fun BioScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Detailed Biography", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("This member is a key part of the Dream Team.")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.popBackStack() }) { Text("Back to Team") }
    }
}

// --- PLACEHOLDER ---
@Composable
fun PlaceholderScreen(name: String, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$name Screen")
            Button(onClick = { navController.popBackStack() }) { Text("Go Back") }
        }
    }
}