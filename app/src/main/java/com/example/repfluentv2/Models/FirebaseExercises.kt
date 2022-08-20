package com.example.myapplication.Adapter_and_Singletons

class ExerciseFirebaseInfo {
    var imagefullURL: String? = null
    var title: String? = null
    var calories: String? = null
    var protein: String? = null
    var fat: String? = null
    var image: String? = null
    var carbs: String? = null
    var mealTime: String? = null
    var totalcarbs = 0
    var quantityFood = 0
    var name: String? = null
    var id: String? = null
    var exerciseOrFood: String? = null
    var instruc: String? = null
    var sets: String? = null
    var reps: String? = null
    var rest: String? = null
    var items: ArrayList<String>? = null
    var quantity: String? = null

    constructor() {}

    constructor(
        name: String?,
        sets: String?,
        reps: String?,
        rest: String?,
        exerciseOrFood: String?,
        imagefullURL: String?,
        instruc: String?
    ) {
        this.name = name
        this.sets = sets
        this.reps = reps
        this.instruc = instruc
        this.imagefullURL = imagefullURL
        this.rest = rest
        this.exerciseOrFood = exerciseOrFood
    }


    override fun toString(): String {
        return "Title: $name Image $imagefullURL instruc $instruc"

}}