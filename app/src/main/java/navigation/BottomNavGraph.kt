package navigation

import Screens.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import components.*

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    Name: String,
    FoodInfo: FoodInfoView,
    FitnessInfo: ExerciseInfoView,
    apiLoginViewModel: SearchExercisesViewModel,
) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route)
    {
        composable(route = BottomBarScreen.Home.route) {
            MenuMainPreview(Name)
        }

        composable(route = BottomBarScreen.FitnessMenu.route) {
            FitnessMenuPreview(
                FitnessInfo,
                apiLoginViewModel,
                navController
            )
        }

        composable(route = FitnessMain.SearchEx.route) {
            val searchExercisesViewModel = hiltViewModel<SearchExercisesViewModel>()


            ExerciseSearcher(navController = navController, apiLoginViewModel = searchExercisesViewModel)
        }

        composable(route = BottomBarScreen.FoodMenu.route) {
            FoodMenu("Food", FoodInfo)
        }
    }
}

@Composable
fun LoginNavGraph(
    navController1: NavHostController,
    name: String,
    LogInViewModel: ViewModelS,
    SignupViewModel: SignupViewModel,
    UserInViewModel: UserInfoViewModel,
    StatsViewModel: StatsViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CreateAccount.LoginPage.route)
    {
        composable(route = CreateAccount.LoginPage.route) {
            LogInPage(navController, LogInViewModel)
        }

        composable(
            route = CreateAccount.Signup.route + "?Email={Email}/?Email1={Email}",
            arguments = listOf(navArgument("Email") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            SignUpPage(navController,
                entry.arguments?.getString("Email"),
                SignupViewModel,
                LogInViewModel)
        }


        composable(
            route = CreateAccount.UserDetails.route + "?Email={Email}/?Username={Username}/?Password={Password}",
            arguments = listOf(
                navArgument("Email") {
                    type = NavType.StringType
                    defaultValue
                },
                navArgument("Username") {
                    type = androidx.navigation.NavType.StringType
                    defaultValue
                },
                navArgument("Password") {
                    type = androidx.navigation.NavType.StringType
                    defaultValue
                }
            )) { entry ->
//            entry.arguments?.getString("Password")?.let {
//                UserDetailsPage(
//                    navController, entry.arguments?.getString("Email"),
//                    entry.arguments?.getString("Username")!!,
//                    it,

            UserDetailsPage(navController,
                entry.arguments?.getString("Email"),
                entry.arguments?.getString("Username"),
                entry.arguments?.getString("Password"),
                UserInViewModel)


        }

        composable(route = CreateAccount.Stats.route + "?UserInfo={UserInfo}",
            arguments = listOf(
                navArgument("UserInfo") {
                    type = NavType.StringType
                    defaultValue
                }
            )
        ) { entry ->
            StatsPage(navController, entry.arguments?.getString("UserInfo"), StatsViewModel)
        }

        composable(route = BottomBarScreen.Home.route) {
            MenuMainPreview("Name")
        }

    }


}