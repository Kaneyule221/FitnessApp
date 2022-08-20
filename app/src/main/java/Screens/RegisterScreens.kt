package Screens

import Screens.ui.theme.RepfluentV2Theme
import Screens.Register
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import components.SignupViewModel
import components.StatsViewModel
import components.UserInfoViewModel
import components.ViewModelS

@Preview
@Composable
fun UserDetailsPage(
    navController: NavController? = null,
    GottenEmail: String? = "",
    Username: String? = "",
    password: String? = "",UserInViewModel: UserInfoViewModel = UserInfoViewModel()
) {
    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFDFEAEE))
                    .verticalScroll(rememberScrollState())
            ) {
                if ((GottenEmail != null) && (Username != null) && (password != null)  ) {
                    UserDetails(
                        navController = navController,
                        GottenEmail = GottenEmail,
                        Username = Username,
                        password = password, UserInViewModel = UserInViewModel
                    )

                }

            }
        }
    }
}


@Composable
fun LogInPage(navController: NavController? = null, TextViewModel: ViewModelS) {
    RepfluentV2Theme {

        val email by remember { mutableStateOf("")}
        val Password by remember { mutableStateOf("")}
        val emailCount by remember { mutableStateOf(0)}
        val passwordCount by remember { mutableStateOf(0)}

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFDFEAEE))
                    .verticalScroll(rememberScrollState())
            ) {

                Login(TextViewModel = TextViewModel,
                    navController = navController,
                    EmailM = mutableStateOf(email),
                    PasswordM = mutableStateOf(Password),
                    EmailCountM = mutableStateOf(emailCount),
                    PasswordCountM = mutableStateOf(passwordCount)
                )



            }
        }
    }
}



@Composable
fun SignUpPage(navController: NavController? = null, GottenEmail: String? = "", sViewModel: SignupViewModel? = SignupViewModel(), LogInViewModel: ViewModelS? = ViewModelS()) {
    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Cyan
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(color = Color(0xFFDFEAEE))

            ) {
                item {
                if (GottenEmail != null) {
                    if ((sViewModel != null) && (LogInViewModel != null)) {
                        SignUp(GottenEmail, navController, ViewModel = sViewModel, LoginViewModel = LogInViewModel)
                    }
                } }

            }
        }
    }
}



@Composable
fun StatsPage(
    navController: NavController? = null,
    UserInformation: String? = null,
    StatsViewModel: StatsViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Cyan
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(40.dp),
            modifier = Modifier
                .background(color = Color(0xFFDFEAEE))
                .verticalScroll(rememberScrollState())
        ) {
            if (UserInformation != null) {
                stats(UserInformation, StatsViewModel =StatsViewModel)
            }


        }
    }
}