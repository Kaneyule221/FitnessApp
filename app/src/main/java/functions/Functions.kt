package com.example.repfluentv2

import android.util.Log
import android.util.Patterns
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.regex.Pattern
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


var pin = 6.dp;
fun FPrint(modifier: Modifier = Modifier, Height: Dp = 1.dp): Dp {
    print("hey")
    Log.d("Hey", pin.toString())
    pin = 10.dp

        return (pin)
}








var name: String = ""

suspend fun GetExerciseName(): String = suspendCoroutine {
    val db = Firebase.firestore
    //create database connection variable
    //Access User collection where userId is equal to id signed in with
    db.collection("users").whereEqualTo("UserId", "3HDPAFWaRdUOY0AfyQscAG1d6gs1")
        .get().addOnCompleteListener {

            //Loop through all documents with-in result and find the workout collection set to a variable
            for (Document1 in it.result!!) {
                print(Document1)
                Log.d("King", Document1.toString())
                val p: DocumentReference = db.collection("users").document(Document1.id)
                val k: CollectionReference = p.collection("Workout")

                //complete listener to check if workout collection found then loop through all documents in collection reference where id matches signed in user
                k.get().addOnCompleteListener { it1 ->
                    //loops through collection untill document found which matches the current day to get the current days workout schedule
                    for (Document2 in it1.result!!) {
                        if (Document2.id == "Saturday") {

                            //set the found document to hashmap so you can loop through and edit
                            val hash: HashMap<String, String> = Document2.data as HashMap<String, String>
                            hash.remove("WorkoutTitle")

                            //Loop through hashmap of workout schedule and print key
                            for (i in hash) {
                                name = i.key
//                                Log.d("Scope", name)
                            }

                        }
                    }
                }
            }
        }
    it.resume(name)
}

fun CheckUserInfo(Email: String, Username: String, Password: String): String {
    val PasswordPatternNum = Regex("[1-9]")
    val PasswordPatternSpaces = Regex("(?=\\S+$)")
    val PasswordPatternChars = Regex(".{4,}")
    val PasswordPatternSpecChar = Regex("(?=.*[@#$%^&+=.])")
    return if (Email.isNotEmpty() && Username.isNotEmpty() && Password.isNotEmpty()) {
        if (!PasswordPatternNum.containsMatchIn(Password)) {
            "Please add Numbers to password"
        } else if(!PasswordPatternSpecChar.containsMatchIn(Password)){
            "Please add Special chars to password "
        }else if(!PasswordPatternSpaces.containsMatchIn(Password)){
            "No Spaces"
        }else if(!PasswordPatternChars.containsMatchIn(Password)){
            "Please add more than 3 characters to password"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            "Non Valid Email"
        }
        else {
            Log.d("Password","Add Numbers")
            "true"
        }
    } else {
        return "Empty Fields"
    }

}