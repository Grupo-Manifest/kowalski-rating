package ecb.manifest.kowalski.rating.ui.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ReviewScreen : Screen("review_screen")
}
