package jets.iti.yousef

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform