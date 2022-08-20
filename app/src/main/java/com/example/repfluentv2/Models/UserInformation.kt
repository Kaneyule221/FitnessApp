package userinfo

import android.app.Application

class Userinformation : Application() {
    var username: String? = null
    var userID: String? = null
    var email: String? = null
    var height: String? = null
    var weight: String? = null
    var age: String? = null
    var bmi: String? = null
    var totalcalories = 0
    var goalWeight: String? = null
    var weightAim: String? = null
    var uDocumentId: String? = null
    var totalMeals: String? = null
    private var Protein: String? = null
    private var Carbs: String? = null
    private var Calories: String? = null
    private var Fat: String? = null
    fun getProtein(): String {
        return Math.round(Protein!!.toDouble() / totalMeals!!.toDouble()).toString()
    }

    fun setProtein(protein: String?) {
        Protein = protein
    }

    fun getCarbs(): String {
        return Math.round(Carbs!!.toDouble() / totalMeals!!.toDouble()).toString()
    }

    fun setCarbs(carbs: String?) {
        Carbs = carbs
    }

    fun getFat(): String {
        return Math.round(Fat!!.toDouble() / 4).toString()
    }

    fun setFat(fat: String?) {
        Fat = fat
    }

    fun getCalories(): String {
        return Math.round(Calories!!.toDouble() / totalMeals!!.toDouble()).toString()
    }

    fun setCalories(calories: String?) {
        Calories = calories
    }

    companion object {
        var instance: Userinformation? = null
            get() {
                if (field == null) field = Userinformation()
                return field
            }
    }
}