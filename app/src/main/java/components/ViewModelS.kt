package components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repfluentv2.GetExerciseName
import com.example.repfluentv2.R
import data.FitnessInfo
import data.MealRepo
import data.Resources
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelS : ViewModel() {
    //State
    var EmailM by mutableStateOf("legoman3210@gmail.com")
    var EmailCountM by mutableStateOf(0)
    var PasswordM by mutableStateOf("")
    var PasswordCountM by mutableStateOf(0)
    var ErrorColor by mutableStateOf(Color.Red)
    var LoginSuccess by mutableStateOf(false)
    var LoginError by mutableStateOf(false)
    //event
    fun onTextChanged(NewString: String, NumLetters: Int) {
        if (NewString.length <= NumLetters + 1) {
            EmailM = NewString
//    return NewString
        }
    }

    fun onPasswordChanged(NewString: String) {
        PasswordM = NewString
    }

    fun onPasswordCountChanged(NewInt: Int) {
        PasswordCountM = NewInt
    }

    fun EmailCountMCountChanged() {
        EmailCountM = EmailM.length
    }

    fun ColorChanged() {
        EmailCountM = EmailM.length
    }
}


class SignupViewModel : ViewModel() {
    var LogEmail by mutableStateOf("")
    var Num by mutableStateOf(0)
    var EmailM by mutableStateOf("legoman3210@gmail.com")
    var Username by mutableStateOf("Kane221")
    var UsernameCount by mutableStateOf(0)
    var EmailCountM by mutableStateOf(0)
    var PasswordM by mutableStateOf("lol.zip23")
    var PasswordCountM by mutableStateOf(0)
    var ErrorMessage by mutableStateOf("")
    var ShowError by mutableStateOf(false)

    @OptIn(ExperimentalFoundationApi::class)
    var bringIntoViewRequester by mutableStateOf(BringIntoViewRequester())
    //event





    fun onPasswordChanged(NewString: String) {
        PasswordM = NewString
    }

    fun UsernameChanged(NewString: String, NumLetters: Int) {
        if (NewString.length <= NumLetters) {
            Username = NewString
        }
    }

    fun EmailChanged(NewString: String, NumLetters: Int) {
        if (NewString.length <= 40) {
            EmailM = NewString
        }
    }
}

class UserInfoViewModel : ViewModel() {
    var Height by mutableStateOf("187")
    var WeightGoal by mutableStateOf("95")
    var Age by mutableStateOf("21")
    var RadioButtonGoalVal by mutableStateOf("")
    var RadioButtonWeightVal by mutableStateOf("Mild")
    var Weight by mutableStateOf("85")
    var ButtonGroupGoalVal by mutableStateOf("Lose")
    var ButtonGroupChangeVal by mutableStateOf("Mild")
    var ButtonGroupActivityVal by mutableStateOf("")
    var LoadBoolean by mutableStateOf(false)


    @OptIn(ExperimentalFoundationApi::class)
    var bringIntoViewRequester by mutableStateOf(BringIntoViewRequester())

    //event



    fun onHeightChanged(NewInt: String, i: Int) {
        if (NewInt.length <= i){
        Height = NewInt
        }
    }

    fun onButtonGroupGoalChange(NewString: String){
        ButtonGroupGoalVal = NewString
        Log.d("Button",ButtonGroupGoalVal)
        if (ButtonGroupGoalVal == "Maintain"){
            WeightGoal = Weight
        }
    }

    fun onButtonGroupChangeVal(NewString: String){
        ButtonGroupChangeVal = NewString

    }

    fun onButtonGroupActivityVal(NewString: String){
        ButtonGroupActivityVal = NewString

    }

    fun onWeightGoalChanged(NewString: String, NumLetters: Int) {
        if (NewString.length <= NumLetters) {
            if (ButtonGroupGoalVal != "Maintain"){
            WeightGoal = NewString
        }}
    }


    fun onWeightChanged(NewInt: String, i: Int) {
        if (NewInt.length <= i){
        Weight = NewInt}
    }


    fun AgeChanged(NewInt: String, NumLetters: Int) {
        if (NewInt.length <= NumLetters) {
            Age = NewInt
        }
    }

    fun RadioButtonGoalVal(NewString: String) {
        RadioButtonGoalVal = NewString
        if (ButtonGroupGoalVal == "Maintain"){
            WeightGoal = Weight
        }
    }

    fun RadioButtonWeightVal(NewString: String) {
        RadioButtonWeightVal = NewString
    }

}

class StatsViewModel(): ViewModel(){
    var LoadToast by mutableStateOf(false)
}

@Composable
fun remeberExposedMenuState(hey:String) = remember{
    UserInfoViewModel()
}





class FoodInfoView(
    val repository:MealRepo = MealRepo(),
): ViewModel(){
    var FoodInfoState by mutableStateOf(MealRepoState())

    fun loadfoodinfo(){
        viewModelScope.launch {
            repository.getMealInfo("").collect(){

               FoodInfoState = FoodInfoState.copy(MealInfoList = it)
        }
    }
}}

data class MealRepoState(
    val MealInfoList:Resources<out List<Any>> = Resources.Loading(),
)

class ExerciseInfoView(
    val repository:FitnessInfo = FitnessInfo(),
): ViewModel(){
    var FitnessInfoState by mutableStateOf(ExerciseRepoState())

    fun loadfoodinfo(){
        viewModelScope.launch {
            repository.getMealInfo("").collect(){

                FitnessInfoState = FitnessInfoState.copy(WorkoutInfo = it)
            }
        }
    }}

data class ExerciseRepoState(
    val WorkoutInfo:Resources<out List<Any>> = Resources.Loading(),
)