package components

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repfluentv2.Models.AuthToken
import com.example.repfluentv2.Models.ExercisesItem
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import data.Data_Or_Exception
import data.ExerciseRepository
import data.LoginToAPi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchExercisesViewModel @Inject constructor(
    private val repos: ExerciseRepository,
    private val loginrepose: LoginToAPi,
) :
    ViewModel() {
    var ListOfExercises: MutableState<Data_Or_Exception<List<ExercisesItem>, Boolean, Exception>> =
        mutableStateOf(Data_Or_Exception(null, true, Exception("")))

    var JsonObjectOfExercises: MutableState<Data_Or_Exception<JsonObject, Boolean, Exception>> =
        mutableStateOf(Data_Or_Exception(null, true, Exception("")))

    init {
        getid()
        SearchExercise("chest_press")
    }

    fun getid() {
        viewModelScope.launch(Dispatchers.IO) {
            if (AuthToken.instance?.AuthToken.toString().isEmpty()) {
                loginrepose.GetAuthKey()
            }
        }

    }

    fun SearchExercise(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                return@launch
            }
            if (ListOfExercises.toString().isNotEmpty()){
            ListOfExercises.value.loading = true

            ListOfExercises.value = repos.GetExercises(query)

            if (ListOfExercises.value.e.toString() != "null") {
                JsonObjectOfExercises.value = repos.GetExercises2(query)
                if (JsonObjectOfExercises.value.data.toString()
                        .isNotEmpty()
                ) JsonObjectOfExercises.value.loading = false
            }
            if (ListOfExercises.value.data.toString().isNotEmpty()) ListOfExercises.value.loading =
                false
        }
    }}


}