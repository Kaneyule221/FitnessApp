package Screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repfluentv2.GetExerciseName
import com.example.repfluentv2.R
import com.example.repfluentv2.ui.theme.RepfluentV2Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import components.*
import com.example.repfluentv2.Models.Meals
import data.Resources
import kotlinx.coroutines.*

@OptIn(ExperimentalPagerApi::class, DelicateCoroutinesApi::class)
@Composable
fun FoodMenu(name: String = "Food", FoodInfo: FoodInfoView) {

    var CurrMealIndex by remember() {
        mutableStateOf(0)
    }

//    val meals1 = remember { mutableStateListOf(Meals()) }
//
//    MealsService.getExercises(meals1)
    val pagerState = rememberPagerState()

    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }

    var exname by remember {
        mutableStateOf("536535")
    }

    val FoodMenuState = FoodInfo.FoodInfoState

    LaunchedEffect(key1 = Unit) {
        FoodInfo.loadfoodinfo()
    }

    var parentJob = Job()
    var viewModelScope: CoroutineScope = CoroutineScope(parentJob + Dispatchers.Main)
    viewModelScope.launch {
        exname = GetExerciseName()

    }

    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "Food Menu", modifier = Modifier.padding(top = 10.dp), fontSize = 30.sp)
                Text(
                    text = "My Meal Plan",
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                    fontSize = 20.sp
                )

                when (FoodMenuState.MealInfoList) {
                    is Resources.Loading -> {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth().padding(horizontal = 5.dp)
                                .height(10.dp),
                            backgroundColor = Color.LightGray,
                            color = Color(0xFFC1E9EE) //progress color
                        )
                    }
                    is Resources.Success -> {
                        Log.d("List",FoodMenuState.MealInfoList.data.toString())
                        FoodMenuState.MealInfoList.data?.size?.let {
                            HorizontalPager(
                                count = it,
                                state = pagerState,
//                                modifier = Modifier.widthIn(200.dp),
                                contentPadding = PaddingValues(horizontal = 32.dp)
                            )
                            { CurrentPage ->
                                val model: Meals = FoodMenuState.MealInfoList.data[CurrentPage] as Meals
//                                val model: Meals = FoodMenuState.FoodInfoList.data[CurrentPage] as Meals
                                var Items = arrayListOf<String>()
                                Log.d("List", "1")
                                var Instructions = model.Quantity.toString().replace("<p>","")
                                    .replace(";",",")
                                    .replace(".",",")
                                    .replace("[","")
                                    .replace("]","")
                                if (Instructions.contains(",") && Instructions != "null"){
                                Items =Instructions.replace("<p>","").split(",") as ArrayList<String>
                                }else if(Instructions.equals("null")){
                                    for( i in 1..5){
                                        Items.add("s $i")
                                    }
                                }

                                FlipCard(
                                    cardFace = cardFace,
                                    onClick = { cardFace = cardFace.next },
                                    modifier = Modifier
                                        .align(CenterHorizontally)
                                        .padding(vertical = 10.dp),
                                    front = {
                                        FlipCardContent(
                                            AsynUrl = model.image.toString(),
                                            Title = model.MealTitle.toString(),
                                            image = painterResource(id = R.drawable.bench),
                                            RadiusColour1 = Color(0xFFCCCCCB),
                                            RadiusColour2 = Color(0xFFFAFAFA),
                                            onClick2 = { cardFace = cardFace.next },
                                        )
                                    },
                                    back = {
                                        Content(
                                            itemsList = Items as ArrayList<String>,
                                            RadiusColour1 = Color(0xffffe1c3),
                                            RadiusColour2 = Color(0xFFF5C085),
                                            onClick2 = { cardFace = cardFace.next },
                                        )
                                    },
                                )
                            }
                        }
                    }
                    else -> {
                        Text(text = FoodMenuState.MealInfoList.throwable?.localizedMessage
                            ?: "Error")
                    }
                }

                components.MenuCard(
                    modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                    onClick = { /*TODO*/ }, Height = 100.dp, Width = 300.dp,
                    RadiusColour1 = Color(0xFFC1E9EE),
                    RadiusColour2 = Color.Cyan,
                    TextTitle = "Meal Planner",
                    CardBody = "Plan and create your own meals by choising from the food database",
                    image = painterResource(id = R.drawable.scales2)
                )

                components.MenuCard(
                    modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                    onClick = { /*TODO*/ }, Height = 100.dp, Width = 300.dp,
                    RadiusColour1 = Color(0xFFC1C5C5),
                    RadiusColour2 = Color(0xFFF1F8F8),
                    TextTitle = "Food Diary",
                    CardBody = "Plan and create your own meals by choising from the food database",
                    image = painterResource(id = R.drawable.dumbell)
                )

                components.MenuCard(
                    modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                    onClick = { /*TODO*/ }, Height = 100.dp, Width = 300.dp,
                    RadiusColour1 = Color(0xFFC1E9EE),
                    RadiusColour2 = Color.Cyan,
                    TextTitle = "Meal generator ",
                    CardBody = "Plan and create your own meals by choising from the food database",
                    image = painterResource(id = R.drawable.dumbell)
                )
            }


        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
//    FoodMenu("Food")

}
