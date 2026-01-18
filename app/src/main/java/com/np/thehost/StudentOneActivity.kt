package com.np.thehost

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.np.thehost.ui.theme.THEHOSTTheme

class StudentOneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            THEHOSTTheme {
                StudentBio(
                    name = "Joshua P. Lutap",
                    role = "Lead Developer",
                    bio = "My name is Joshua P. Lutap. I'm 22 years old and I want to be a Lead developer that can help and have fun learning.",
                    imageRes = R.drawable.joshua
                )
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun StudentBio(name: String, role: String, bio: String, imageRes: Int) {
    val context = LocalContext.current as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            role,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            bio,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (context is ComponentActivity) {
                    context.finish() // Go back to HomeScreen
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Back", color = Color.White)
        }
    }
}

