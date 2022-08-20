package navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import components.*

@Composable
fun MainScreen(
    Name: String, ViewModel: ViewModelS, FoodInfo: FoodInfoView,
    FitnessInfo: ExerciseInfoView,
    apiLoginViewModel: SearchExercisesViewModel,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) })
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController = navController, Name, FoodInfo, FitnessInfo,
                apiLoginViewModel
            )

        }
    }
}

@Composable
fun MainScreen2(
    Name: String,
    TextViewModel: ViewModelS,
    SignUpViewModel: SignupViewModel,
    UserInViewModel: UserInfoViewModel,
    StatsViewModel: StatsViewModel,
    FoodInfo: FoodInfoView,
    FitnessInfo: ExerciseInfoView,
    apiLoginViewModel: SearchExercisesViewModel,
) {
    if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
        if (!(TextViewModel.LoginSuccess)) {
            val navController = rememberNavController()
            LoginNavGraph(
                navController1 = navController,
                Name,
                TextViewModel,
                SignUpViewModel,
                UserInViewModel,
                StatsViewModel,
            )
        } else {

            val navController2 = rememberNavController()
            Scaffold(
                bottomBar = { BottomBar(navController = navController2) })
            { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    BottomNavGraph(navController = navController2, Name, FoodInfo, FitnessInfo,
                        apiLoginViewModel)

                }
            }
        }
    } else {
        val navController2 = rememberNavController()
        Scaffold(
            bottomBar = { BottomBar(navController = navController2) })
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                BottomNavGraph(navController = navController2, Name, FoodInfo, FitnessInfo,
                    apiLoginViewModel
                )

            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.FoodMenu,
        BottomBarScreen.FitnessMenu,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = Color.Black,
        unselectedContentColor = Color.Black.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
            }
        }
    )
}



