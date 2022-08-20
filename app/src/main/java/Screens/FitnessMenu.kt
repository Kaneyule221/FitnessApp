package Screens

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.myapplication.Adapter_and_Singletons.ExerciseFirebaseInfo
import com.example.repfluentv2.FPrint
import com.example.repfluentv2.R
import com.example.repfluentv2.ui.theme.GradientColor
import com.example.repfluentv2.ui.theme.RepfluentV2Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import components.*
import data.Resources
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import navigation.FitnessMain

@Composable
fun FitnessMenu1(name: String) {
    Surface(
        modifier = Modifier.wrapContentSize(),
        color = MaterialTheme.colors.background
    ) {
        Row() {
            components.BottomCard(Height = 275.dp,
                Width = 300.dp,
                Title = "Bench Tips",
                image = painterResource(id = R.drawable.bench),
                RadiusColour1 = Color(0xffffe1c3),
                RadiusColour2 = Color(0xFFF5C085),
                CardBody = "Log and compare your Exercise personal bests",
                onClick2 = { FPrint() },
                onClick3 = {}, onClick4 = {


                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FitnessMenu(
    name: String = "Food", FitnessInfo: ExerciseInfoView = ExerciseInfoView(),
    apiLoginViewModel: SearchExercisesViewModel, navController: NavController,
) {

    var CurrMealIndex by remember() {
        mutableStateOf(0)
    }

//    val meals1 = remember { mutableStateListOf(Meals()) }
//
//    MealsService.getExercises(meals1)
    val pagerState = rememberPagerState(initialPage = 0)


    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }

    var exname by remember {
        mutableStateOf("536535")
    }
//    apiLoginViewModel.apiLogin()

    val ExerciseMenuState = FitnessInfo.FitnessInfoState

    LaunchedEffect(key1 = Unit) {
        FitnessInfo.loadfoodinfo()
//        apiLoginViewModel.GetPosts()


    }

//

    apiLoginViewModel.SearchExercise("burpee")
    Log.d("Scope", exname)

    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .background(GradientColor)
                    .padding()
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()

                ) {

                    Card(modifier = Modifier.fillMaxWidth(), elevation = 10.dp) {
                        Column(Modifier
                            .background(GradientColor)) {
                            Text(text = "Fitness",
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                                fontSize = 25.sp)
                        }
                    }


                }
                Spacer(Modifier.height(10.dp))
                val scope = rememberCoroutineScope()



                ConstraintLayout(modifier = Modifier.fillMaxSize().padding(start = 10.dp),
                ){
                    val (Text, Button, Button1) = createRefs()
                    Text(modifier = Modifier.constrainAs(Text)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                        text = "My Exercise Plan",
                        fontSize = 16.sp
                    )

                    IconButton(modifier = Modifier.wrapContentSize().constrainAs(Button)
                    {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                        onClick =
                        {


                            scope.launch {
                                if (pagerState.currentPage < pagerState.pageCount -1){
                                    pagerState.scrollToPage(pagerState.currentPage + 1)
                                }}
                        },
                    )
                    {
                        Icon(painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp).wrapContentSize())
                    }

                    IconButton(modifier = Modifier.wrapContentSize().constrainAs(Button1)
                    {
                        top.linkTo(parent.top)
                        end.linkTo(Button.end, margin = 30.dp)
                    },
                        onClick =
                        {
                            scope.launch {
                                if (pagerState.currentPage > 0){
                                    pagerState.scrollToPage(pagerState.currentPage - 1)
                                }}
                        },
                    )
                    {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp).wrapContentSize())
                    }

                }
                when (ExerciseMenuState.WorkoutInfo) {
                    is Resources.Loading -> {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                                .height(10.dp),
                            backgroundColor = Color.LightGray,
                            color = Color(0xFFC1E9EE) //progress color
                        )
                    }
                    is Resources.Success -> {
                        Log.d("List", ExerciseMenuState.WorkoutInfo.data.toString())
                        ExerciseMenuState.WorkoutInfo.data?.size?.let {
                                HorizontalPager(
                                    count = it,
                                    state = pagerState,
                                    modifier = Modifier.widthIn(200.dp),
                                    contentPadding = PaddingValues(horizontal = 32.dp)
                                )
                                { CurrentPage ->
                                    val model: ExerciseFirebaseInfo =
                                        ExerciseMenuState.WorkoutInfo.data[CurrentPage] as ExerciseFirebaseInfo
                                    var Items = arrayListOf<String>()
                                    Log.d("List", "1")
                                    var Instructions =
                                        model.instruc.toString().replace("<p>", "")
                                            .replace("</p>", "")
                                            .replace(";", ",")
                                            .replace(".", ",")
                                            .replace("[", "")
                                            .replace("]", "").replace("", "")
                                    if (Instructions.contains(",") && Instructions != "null") {
                                        Items = Instructions.replace("<p>", "")
                                            .split(",") as ArrayList<String>
                                    } else if (Instructions.equals("null")) {
                                        for (i in 1..5) {
                                            Items.add("s $i")
                                        }
                                    }

                                    FlipCard(
                                        cardFace = cardFace,
                                        onClick = { cardFace = cardFace.next },
                                        modifier = Modifier
//                                        .align()
                                            .padding(vertical = 10.dp),
                                        front = {
                                            FlipCardContent(
                                                AsynUrl = model.imagefullURL.toString(),
                                                Title = model.name.toString(),
                                                image = painterResource(id = R.drawable.bench),
                                                RadiusColour1 = Color(0xFFCCCCCB),
                                                RadiusColour2 = Color(0xFFFAFAFA),
                                                onClick2 = { cardFace = cardFace.next },
                                            )
                                        },
                                        back = {
                                            ContentScrollView(scope = scope,
                                                Title = model.name.toString() ,
                                                itemsList = Items as ArrayList<String>,
                                                RadiusColour1 = Color(0xffffe1c3),
                                                RadiusColour2 = Color(0xFFF5C085),
                                                onClick2 = { cardFace = cardFace.next },
                                            )
                                        },
                                    )
                                }


                        }

                        scope.launch {
                            if (pagerState.currentPage < pagerState.pageCount -1){
                                if (pagerState.pageCount != 0){
                                    pagerState.scrollToPage(0)
                                    pagerState.scrollToPage(pagerState.currentPage + 2)
                                    Log.d("Pager",pagerState.currentPage.toString() + pagerState.pageCount.toString())
                                }}}

                    }
                    else -> {
                        Text(text = ExerciseMenuState.WorkoutInfo.throwable?.localizedMessage
                            ?: "Error")
                    }
                }
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .heightIn()) {
                        SmallCard(70.dp,
                            140.dp,
                            onClick = {},
                            Title = "Workout Planner",
                            image = painterResource(id = R.drawable.planner),
                            Colour = Color(0xFFC3FAFF),
                            Colour2 = Color(0xFF39D6F1),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            IconSize = 30.dp)


                        SmallCard(Height = 50.dp,
                            Width = 135.dp,
                            onClick = {
                            },
                            Title = "Food Diary",
                            image = painterResource(id = R.drawable.diary),
                            Colour = Color(0xFFC3FAFF),
                            Colour2 = Color(0xFF39D6F1),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            IconSize = 30.dp)


                        SmallCard(Height = 70.dp,
                            Width = 135.dp,
                            onClick = {},
                            Title = "Food Diary",
                            image = painterResource(id = R.drawable.diary),
                            Colour = Color(0xFFC3FAFF),
                            Colour2 = Color(0xFF39D6F1),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            IconSize = 30.dp)
                    }

                    MenuCard(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClick = {
                            navController.navigate(FitnessMain.SearchEx.route)

                        }, Height = 100.dp, Width = 300.dp,
                        RadiusColour1 = Color(0xFFF1FAFD),
                        RadiusColour2 = Color.Cyan,
                        TextTitle = "Search Exercises ",
                        CardBody = "Search Exercises from the exercise database",
                        image = painterResource(id = R.drawable.ic_baseline_published_with_changes_24),
                        IconSize = 125.dp
                    )
                }
            }


        }
    }
}

@Composable

fun FitnessMenuPreview(
    FitnessInfo: ExerciseInfoView = ExerciseInfoView(),
    apiLoginViewModel: SearchExercisesViewModel,
    navController: NavController,
) {
    Row() {
        FitnessMenu("Fitness", FitnessInfo, apiLoginViewModel, navController)
//
    }
}