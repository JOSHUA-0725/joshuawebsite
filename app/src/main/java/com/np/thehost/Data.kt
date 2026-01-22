package com.np.thehost

// --- DATA MODEL ---
data class UserProfile(
    val id: Int,
    val name: String,
    val role: String,
    val bio: String,
    val imageRes: Int
)

val dreamTeam = listOf(
    UserProfile(
        1,
        "Joshua P. Lutap",
        "Lead Developer",
        "My name is Joshua P. Lutap. I'm 22 years old and I want to be a Lead developer that can help and have fun learning.",
        R.drawable.joshua
    ),
    UserProfile(
        2,
        "Joshua B. Layog",
        "UI/UX Designer",
        "My name is Joshua B. Layog. I'm 21 years old and I want to be a developer that can help and have fun learning.",
        R.drawable.layog
    ),
    UserProfile(
        3,
        "Lucero, Zoe journey E.",
        "Data Analyst",
        "Pedro analyzes data trends and ensures accurate reporting.",
        R.drawable.lucero
    ),
    UserProfile(
        4,
        "Jasvin Honrada ",
        "Project Manager",
        "Elena oversees planning, deadlines, and team coordination.",
        R.drawable.honrada
    ),
    UserProfile(
        5,
        "Jose Rizal",
        "System Architect",
        "Jose designs scalable and maintainable system structures.",
        R.drawable.ic_launcher_foreground
    )
)
