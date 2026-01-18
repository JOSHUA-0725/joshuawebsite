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
        "Juan Dela Cruz",
        "Lead Developer",
        "Juan is responsible for the core architecture and backend logic of the app.",
        R.drawable.ic_launcher_foreground
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
        "Pedro Penduko",
        "Data Analyst",
        "Pedro analyzes data trends and ensures accurate reporting.",
        R.drawable.ic_launcher_foreground
    ),
    UserProfile(
        4,
        "Elena Adarna",
        "Project Manager",
        "Elena oversees planning, deadlines, and team coordination.",
        R.drawable.ic_launcher_foreground
    ),
    UserProfile(
        5,
        "Jose Rizal",
        "System Architect",
        "Jose designs scalable and maintainable system structures.",
        R.drawable.ic_launcher_foreground
    )
)
