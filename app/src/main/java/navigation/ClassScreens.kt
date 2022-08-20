package navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object FoodMenu: BottomBarScreen(
        route = "food",
        title = "FoodMenu",
        icon = Icons.Default.ShoppingCart
    )
    object FitnessMenu: BottomBarScreen(
        route = "fitness",
        title = "Fitness",
        icon = Icons.Default.ArrowForward
    )
}


sealed class FitnessMain(
    val route: String,
    val title: String,
){

    object Fitnessmenu: FitnessMain(
        route = "Fitnessmenu",
        title = "Fitnessmenu"
    )

    object FoodDiary: FitnessMain(
        route = "FoodDiary",
        title = "FoodDiary"
    )

    object SearchEx: FitnessMain(
        route = "SearchEx",
        title = "SearchEx"
    )

    object WorkoutPlanner: FitnessMain(
        route = "WorkoutPlanner",
        title = "Workout Planner"
    )
}


sealed class CreateAccount(
    val route: String,
    val title: String,
){
    object LoginPage: CreateAccount(
        route = "Login",
        title = "LogIn",
    )
    object Signup: CreateAccount(
        route = "Signup",
        title = "SignUp",

        )
    object UserDetails: CreateAccount(
        route = "Userdetails",
        title = "UserDetails",
    )
    object Stats: CreateAccount(
        route = "Stats",
        title = "Stats",
    )

    fun withargs(vararg args: String ): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append(arg)
            }
            Log.d("Route",this.toString())
        }
    }
}

