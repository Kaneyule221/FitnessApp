package Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.repfluentv2.R
import com.example.repfluentv2.ui.theme.GradientColor
import com.example.repfluentv2.ui.theme.RepfluentV2Theme
import components.RepTextField
import components.SearchExercisesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoilApi::class)
//@Preview
@Composable
fun ExerciseSearcher(
    apiLoginViewModel: SearchExercisesViewModel = hiltViewModel(),
    navController: NavHostController?,
) {
    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {

            Column(Modifier.background(GradientColor)) {
                Row(modifier = Modifier
                    .wrapContentHeight()
                ) {
                    Card(modifier = Modifier.fillMaxWidth(), elevation = 10.dp) {
                        Column(Modifier
                            .background(GradientColor)) {
                            Row() {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(painterResource(id = R.drawable.previousicon),
                                        contentDescription = "",
                                        modifier = Modifier.size(30.dp))
                                }
                                Text(text = "Exercise Searcher",
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .align(CenterVertically),
                                    fontSize = 20.sp)
                            }
                        }
                    }

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painterResource(id = R.drawable.ic_baseline_search_24),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp))
                        }

                        RepTextField(CardWidth = 320.dp,
                            captionText = "", NoCaption = true,
                            TextFieldString = "Search",
                            TextFieldStringCount = mutableStateOf(2),
                            KeyBoardType = KeyboardType.Number, show = false,
                            onValueChanged = { "UserInViewModel.AgeChanged(it, 2)" }
                        )

                    }


                }
                LazyColumn(
                    modifier = Modifier
                        .background(GradientColor)
                        .padding()
                        .fillMaxWidth()
                ) {
                    items(5) {
                        Card(shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .shadow(elevation = 30.dp, shape = RoundedCornerShape(30.dp))
                                .clip(RoundedCornerShape(30.dp))
                                .padding(horizontal = 10.dp, vertical = 10.dp)) {
                            Column(Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Top) {
                                val painter = rememberImagePainter(
                                    data = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F07%2F13%2FUltimate-Veggie-Burgers-FT-Recipe-0821.jpg&q=60",
                                    builder = {

                                    })


                                ConstraintLayout(
                                    modifier = Modifier
                                        .align(Start)
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    val (Button, Image, Text) = createRefs()
                                    Image(
                                        painter = painterResource(id = R.drawable.bench),
                                        contentDescription = "Image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .constrainAs(Image) {

                                            }
                                            .height(150.dp),
                                        contentScale = ContentScale.FillBounds
                                    )

                                    Text(text = "Barbell Bench Press",
                                        modifier = Modifier
                                            .padding(vertical = 5.dp)
                                            .constrainAs(Text) {
                                                top.linkTo(Image.bottom)
                                                bottom.linkTo(parent.bottom)
                                            }
                                            .padding(start = 5.dp),
                                        fontSize = 15.sp)

                                    androidx.compose.material.Button(
                                        onClick = {},
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                            0xFF1d1d1d)),
                                        modifier = Modifier
                                            .width(70.dp)
                                            .padding(end = 5.dp)
                                            .constrainAs(Button) {
                                                top.linkTo(Image.bottom)
                                                end.linkTo(parent.end)
                                            }
                                    )
                                    {
                                        Text(text = "View", color = Color.White, fontSize = 10.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MenuSec() {
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

                ConstraintLayout(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp),
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

                    IconButton(modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(Button)
                        {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        },
                        onClick =
                        {

                        },
                    )
                    {
                        Icon(painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .wrapContentSize())
                    }

                    IconButton(modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(Button1)
                        {
                            top.linkTo(parent.top)
                            end.linkTo(Button.end, margin = 30.dp)
                        },
                        onClick =
                        {
//                            scope.launch {
//                                if (pagerState.currentPage < pagerState.pageCount -1){
//                                    pagerState.scrollToPage(pagerState.currentPage + 1)
//                                }}
                        },
                    )
                    {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .wrapContentSize())
                    }

                }
            }

        }
    }
}

//@Preview
@Composable
fun ExerciseViewer() {
    RepfluentV2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .background(GradientColor)
                    .padding()
                    .fillMaxWidth()
            ) {
                Card(shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .shadow(elevation = 30.dp, shape = RoundedCornerShape(30.dp))
                        .clip(RoundedCornerShape(30.dp))
                        .padding(horizontal = 10.dp, vertical = 10.dp)) {
                    Column(Modifier, verticalArrangement = Arrangement.Top) {
                        val painter = rememberImagePainter(
                            data = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F07%2F13%2FUltimate-Veggie-Burgers-FT-Recipe-0821.jpg&q=60",
                            builder = {

                            })
                        ConstraintLayout(
                            modifier = Modifier
                                .align(Start)
                                .fillMaxWidth()

                        ) {
                            val (Button, Image, Text) = createRefs()
                            Image(
                                painter = painterResource(id = R.drawable.bench),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .constrainAs(Image) {

                                    }
                                    .height(200.dp),
                                contentScale = ContentScale.FillBounds
                            )

                            Text(text = "Chest Fly",
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .constrainAs(Text) {
                                        top.linkTo(Image.bottom)
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .padding(start = 5.dp),
                                fontSize = 15.sp)

                            androidx.compose.material.Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                    0xFF1d1d1d)),
                                modifier = Modifier
                                    .width(70.dp)
                                    .padding(end = 5.dp)
                                    .constrainAs(Button) {
                                        top.linkTo(Image.bottom)
                                        end.linkTo(parent.end)
                                    }
                            )
                            {
                                Text(text = "Info", color = Color.White, fontSize = 10.sp)
                            }
                        }


                    }
                }
                var arraylist =
                    arrayListOf<String>("Sit up tall and relax your neck and shoulders. "
                            + "Your feet should be flat on the floor.",
                        "Grab the handles so that your palms are " +
                                "facing forward. Note that some machines " +
                                "have a foot bar that you need to push in " +
                                "order to release the handles and bring them " +
                                "forward..",
                        "Press your arms together in front of your chest " +
                                "with a slow, controlled movement. Keep a " +
                                "slight, soft bend in the elbows with wrists " +
                                "relaxed.",
                        "Pause for one second once your arms are " +
                                "fully \"closed\" in front of your chest.",
                        "Bring your arms slowly back to the starting " +
                                "position, opening your chest and keeping " +
                                "posture strong and upright.",
                        "Sit up tall and relax your neck and shoulders. "
                                + "Your feet should be flat on the floor.",
                        "Grab the handles so that your palms are " +
                                "facing forward. Note that some machines " +
                                "have a foot bar that you need to push in " +
                                "order to release the handles and bring them " +
                                "forward..",
                        "Press your arms together in front of your chest " +
                                "with a slow, controlled movement. Keep a " +
                                "slight, soft bend in the elbows with wrists " +
                                "relaxed.",
                        "Pause for one second once your arms are " +
                                "fully \"closed\" in front of your chest.",
                        "Bring your arms slowly back to the starting " +
                                "position, opening your chest and keeping " +
                                "posture strong and upright."

                    )
                Card(shape = RoundedCornerShape(10.dp), elevation = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                        .padding(horizontal = 20.dp, vertical = 5.dp)) {
                    Column(Modifier, verticalArrangement = Arrangement.Top) {
                        LazyColumn(Modifier.fillMaxWidth()) {
                            items(arraylist.size) { index ->
                                Row() {
                                    Text(text = "${index + 1}.",
                                        color = Color.Black,
                                        fontSize = 20.sp, modifier = Modifier
                                            .padding(start = 10.dp)
                                            .align(CenterVertically))
                                    Text(text = arraylist[index],
                                        color = Color.Black,
                                        fontSize = 13.sp, modifier = Modifier
                                            .padding(horizontal = 10.dp, vertical = 7.dp)
                                            .fillMaxWidth())
                                }

                            }

                        }


                    }
                }
            }
        }
    }
}
