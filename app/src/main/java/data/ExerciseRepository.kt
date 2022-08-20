package data

import android.preference.PreferenceManager
import android.util.Log
import com.example.repfluentv2.Models.ExercisesItem
import com.example.repfluentv2.Models.Exerciseses
import com.example.repfluentv2.Models.ExercisesesJson
import com.example.repfluentv2.Models.LoginRequest
import com.example.repfluentv2.util.ApiService
import com.example.repfluentv2.util.SessionManage
import com.google.gson.JsonObject
import retrofit2.http.Query
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val api: ApiService ) {
    private val dataOrException = Data_Or_Exception<List<ExercisesItem>, Boolean, Exception>()

    private val dataOrException2 = Data_Or_Exception<JsonObject, Boolean, Exception>()
    suspend fun GetExercises(searchQuery: String): Data_Or_Exception<List<ExercisesItem>, Boolean, Exception>{
        try {
            dataOrException.loading = true
            dataOrException.data = api.fetchPosts(searchQuery).exercises
            if(dataOrException.data!!.isNotEmpty()) dataOrException.loading = false

        }catch (e: Exception){
            dataOrException.e = e

        }
        return dataOrException
    }




    suspend fun GetExercises2(searchQuery: String): Data_Or_Exception<JsonObject, Boolean, Exception> {
        try {
            dataOrException2.loading = true
            dataOrException2.data = api.fetchPosts2(searchQuery).exercises
            if(dataOrException2.data!!.toString().isNotEmpty()) dataOrException.loading = false


        }catch (e: Exception){
            dataOrException2.e = e

        }
        return dataOrException2
    }
}

class LoginToAPi @Inject constructor(private val api: ApiService){
    private var Authkey = ""
    private val sessionManager = SessionManage()
    suspend fun GetAuthKey():String{
        try {
        Authkey = api.login(LoginRequest("murrainexrx","67o4d84e")).token
        sessionManager.saveAuthToken(Authkey)

        }catch (e: Exception){

        }
        return Authkey
    }
}