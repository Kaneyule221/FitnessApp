package Screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.repfluentv2.R
import com.example.repfluentv2.ui.theme.RepfluentV2Theme
import components.BottomCard

import com.example.repfluentv2.Models.Meals


class MainMenu : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RepfluentV2Theme {
                Surface(color = Color(0xffbfeffb), modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Greet(
                            "mikjoj ",
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 10.dp, bottom = 5.dp), textAlign = TextAlign.Center
                        )

                        MenuCard("Exname")
                    }

                }
            }
        }
    }

}


@Composable
fun MenuCard(Functions: String) {
    var Height by remember() {
        mutableStateOf(275.dp)
    }
    var ShowMeals by remember() {
        mutableStateOf(false)
    }

    var Changed by remember() {
        mutableStateOf(false)
    }

    var ShowIngredients by remember() {
        mutableStateOf(false)
    }

    var ImageChange by remember() {
        mutableStateOf("")
    }
    var BottomSecName by remember() {
        mutableStateOf("BenchTips")
    }

    var CenterBoxName by remember() {
        mutableStateOf("View")
    }

    var CurrMealIndex by remember() {
        mutableStateOf(2)
    }

    val meals1 = remember { mutableStateListOf(Meals()) }




    RepfluentV2Theme() {

        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFDFEAEE)) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Greet(
                            "Kyle ",
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                        components.TopCard(
                            onClick = { /*TODO*/ }, Height = 90.dp, Width = 300.dp,
                            imageVector = Icons.Default.ThumbUp,
                            TextTitle = "87",
                            CardBody = "Previous"
                        )

                        Spacer(
                            modifier = Modifier
                                .width(2.dp)
                                .padding(top = 10.dp)
                        )

                        components.MenuCard(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /*TODO*/ },
                            Height = 100.dp,
                            Width = 300.dp,
                            imageVector = Icons.Default.Star,
                            RadiusColour1 = Color(0xffffe1c3),
                            RadiusColour2 = Color(0xFFF5C085),
                            image = painterResource(id = R.drawable.scales2),
                        )

                        Spacer(
                            modifier = Modifier
                                .width(20.dp)
                                .padding(top = 10.dp)
                        )

                        components.MenuCard(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /*TODO*/ }, Height = 150.dp, Width = 300.dp,
                            RadiusColour1 = Color(0xFFC1E9EE),
                            RadiusColour2 = Color.Cyan,
                            TextTitle = "Exercise Log",
                            CardBody = "Log and compare your Exercise personal bests",
                            image = painterResource(id = R.drawable.dumbell)
                        )

                        Spacer(
                            modifier = Modifier
                                .width(20.dp)
                                .padding(top = 10.dp)
                        )

//                        if (!ShowMeals) {
//                            components.BottomCard(
//                                Height = Height,
//                                Width = 300.dp,
//                                Title = "Current Meals",
//                                image = painterResource(id = R.drawable.bench),
//                                RadiusColour1 = Color(0xffffe1c3),
//                                RadiusColour2 = Color(0xFFF5C085),
//                                CardBody = "Log and compare your Exercise personal bests",
//                                onClick2 = {
//                                    ShowMeals = true
//                                    Changed = true
//                                    CenterBoxName = "Back"
//                                    MealsService.getExercises(meals1)
//                                    meals1.forEachIndexed { index, meals ->
////                                Log.d("MealNames", meals.Calories)
//                                        if (index == 0) {
//                                            CurrMealIndex = index
//                                            BottomSecName = meals.MealTitle
//                                            ImageChange = meals.image
//
//                                        }
//                                    }
//
//                                },
//                                onClick3 = {
//                                },
//                                onClick4 = {
//                                }
//                            )
//                        }
//                        if (ShowMeals) {
//
//                            components.BottomCard(
//                                Height = 275.dp,
//                                Width = 300.dp,
//                                Title = BottomSecName,
//                                image = painterResource(id = R.drawable.bench),
//                                RadiusColour1 = Color(0xffffe1c3),
//                                CenterBoxName = CenterBoxName,
//                                RadiusColour2 = Color.White,
//                                AsynUrl = ImageChange,
//                                SecondButtons = Changed,
//                                CardBody = "Log and compare your Exercise personal bests",
//                                onClick2 = {
//                                    ShowMeals = false
//
//                                },
//                                onClick3 = {
//                                    if (meals1.size - 1 > CurrMealIndex) {
//                                        CurrMealIndex += 1
//                                        Log.d("MealNames", meals1[CurrMealIndex].Calories)
//
//                                        BottomSecName = meals1[CurrMealIndex].MealTitle
//                                        ImageChange = meals1[CurrMealIndex].image
//
//
//                                    }
//                                },
//                                onClick4 = {
//
//                                    if (meals1.size > CurrMealIndex) {
//                                        if (CurrMealIndex > 0) {
//                                            CurrMealIndex -= 1
//                                            BottomSecName = meals1[CurrMealIndex].MealTitle
//                                            ImageChange = meals1[CurrMealIndex].image
//
//                                        }
//                                    }
//
//                                })
//
//
//                        }
//                    }
//
//                }
//
//                items(meals1) { item: Meals ->
//                    RecipeListItem(recipe = item)
//                }

                    }}
            }
        }
    }
}


@Composable
fun RecipeListItem(recipe: Meals) {
    BottomCard(
        onClick2 = { /*TODO*/ },
        onClick3 = { /*TODO*/ },
        onClick4 = { /*TODO*/ },
        Title = recipe.MealTitle,
        AsynUrl = recipe.image,

    )
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Greet(name: String, modifier: Modifier, textAlign: TextAlign) {
    Text(text = "Welcome Back $name!", modifier = modifier, textAlign = textAlign)
}


@Composable
fun Display(Name: String = "s") {
    RepfluentV2Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
//            Column(
//                modifier = Modifier
//                    .background(color = Color(0xFFDFEAEE))
//                    .verticalScroll(rememberScrollState())
//            ) {

                MenuCard(Name)


        }
    }
}
