package data

import android.util.Log
import com.example.myapplication.Adapter_and_Singletons.ExerciseFirebaseInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject

class FitnessInfo {
    private val db = Firebase.firestore
    fun getUserId():String = Firebase.auth.currentUser?.uid.orEmpty()
        fun getMealInfo(
            userId: String,
        ): Flow<Resources<out List<Any>>> = callbackFlow {
            var snapshotstateListner: ListenerRegistration? = null
            val s =
                db.collection("users").whereEqualTo("UserId", "3HDPAFWaRdUOY0AfyQscAG1d6gs1").get()
                    .addOnCompleteListener {
                        for (Document1 in it.result!!) {
                            val p: DocumentReference =
                                db.collection("users").document(Document1.id)
                            val k: CollectionReference = p.collection("Workout")
                            val ExerciseFirebaseInfo = ArrayList<ExerciseFirebaseInfo>()

                            try {
                                snapshotstateListner = k.addSnapshotListener { snapshotstate, e ->
                                    val response = if (snapshotstate != null) {

                                        snapshotstate.forEachIndexed(){item,Doc ->
                                            if (Doc.id == "Tuesday"){
                                            Doc.data.forEach() { (item2, Doc2) ->
                                                if (item2.toString() != "WorkoutTitle"){
                                                val s = Doc2.toString()
                                                val jsonObject: JSONObject
                                                jsonObject = JSONObject(s)
                                                Log.d("List",item2)
                                                val foodOrExerciseinfo: ExerciseFirebaseInfo =
                                                    ExerciseFirebaseInfo(
                                                        jsonObject.getString("Name"),
                                                        jsonObject.getString("Sets"),
                                                        jsonObject.getString("Reps"),
                                                        jsonObject.getString("Instructions"),

                                                        jsonObject.getString("Rest"),jsonObject.getString("Image"),
                                                        jsonObject.getString("Instructions"),
                                                    )
                                                ExerciseFirebaseInfo.add(foodOrExerciseinfo)
                                            }}
                                        }}
                                        val meals = ExerciseFirebaseInfo
                                        Resources.Success(data = meals)
                                    } else {
                                        Log.d("List", e.toString())
                                        Resources.Error(throwable = e?.cause)
                                    }
                                    trySend(response)
                                }

                            } catch (e: Exception) {
                                trySend(Resources.Error(e.cause))
                                e.printStackTrace()
                            }
                        }
                    }
            awaitClose {
                snapshotstateListner?.remove()
            }
        }
    }


sealed class Resources<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>:Resources<T>()
    class Success<T>(data: T?):Resources<T>(data = data)
    class Error<T>(throwable: Throwable?):Resources<T>(throwable = throwable)
}

