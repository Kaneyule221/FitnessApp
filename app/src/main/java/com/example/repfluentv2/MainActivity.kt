package com.example.repfluentv2

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.repfluentv2.ui.theme.RepfluentV2Theme
import components.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import navigation.MainScreen2
import java.util.prefs.Preferences






//val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "MainActivity")
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val LogInViewModel by viewModels<ViewModelS>()
    val SignupViewModel by viewModels<SignupViewModel>()
    val UserInViewModle by viewModels<UserInfoViewModel>()
    val StatsViewModel by viewModels<components.StatsViewModel>()
    val apiLoginViewModel by viewModels<SearchExercisesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {

            val FoodInfoViewModel = viewModel(modelClass = FoodInfoView::class.java )
            val FitnessInfo = viewModel(modelClass = ExerciseInfoView::class.java )
            RepfluentV2Theme {
                MainScreen2("d",LogInViewModel,SignupViewModel,UserInViewModle,StatsViewModel,FoodInfoViewModel,FitnessInfo,apiLoginViewModel)
//                MainScreen("Name: String",LogInViewModel)
        }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){ view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets

        }
    }


}

