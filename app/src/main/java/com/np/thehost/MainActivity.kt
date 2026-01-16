package com.np.thehost

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.np.thehost.ui.theme.THEHOSTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            THEHOSTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Calling our new HomeScreen Composable
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Column stacks elements vertically
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Centers buttons vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers buttons horizontally
    ) {
        Text(
            text = "THE DREAM TEAM APP",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // List of button labels
        val buttonLabels = listOf("Profile", "Scanner", "Records", "Settings", "Logout")

        // Loop to create 5 buttons
        buttonLabels.forEach { label ->
            Button(
                onClick = {
                    Toast.makeText(context, "$label Clicked!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    THEHOSTTheme {
        HomeScreen()
    }
}