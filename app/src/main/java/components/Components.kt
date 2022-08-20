package components


import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.repfluentv2.R
import com.example.repfluentv2.ui.theme.aaargh
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.*


val IconbuttonSizeModifier = Modifier.size(100.dp)

enum class CardFace(val angle: Float) {

    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    Height: Dp = 100.dp,
    TextSize: TextUnit = 13.sp,
    IconSize: Dp = 95.dp,
    image: Painter = painterResource(id = R.drawable.scales2),
    RadiusColour1: Color = Color(0xffffe1c3),
    RadiusColour2: Color = Color(0xfffba94f),
    Width: Dp = 150.dp,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 10.dp,
    imageVector: ImageVector = Icons.Filled.Refresh,
    TextTitle: String = "Weight Log",
    CardBody: String = "Log and compare your body weight",
) {
    Card(
        modifier = modifier
            .padding(all = 5.dp)
            .height(Height)
            .width(Width)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick.invoke() }
            .then(IconbuttonSizeModifier),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = backgroundColor,
        elevation = elevation
    )
    {
        Row(
            modifier = Modifier
                .height(20.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xffbfeffb), Color(0xfff1fafd)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                ), horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .width(IconSize)
                    .padding(start = 15.dp, end = 5.dp)
                    .align(CenterVertically)
            )

            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .padding(top = 10.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .align(CenterVertically)
                    .wrapContentSize()
                    .fillMaxWidth()
            )
            {
                Text(text = TextTitle, style = MaterialTheme.typography.body1)
                Text(
                    text = CardBody,
                    fontSize = TextSize,
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center, modifier = Modifier.padding(start = 3.dp)
                )
            }
        }
    }

}

@Composable
fun TopCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    Height: Dp = 100.dp,
    Width: Dp = 100.dp,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 10.dp,
    painter: Int = com.example.repfluentv2.R.drawable.ic_baseline_published_with_changes_24,
    imageVector: ImageVector = Icons.Filled.Refresh,
    TextTitle: String = "Weight Log",
    CardBody: String = "Log and compare your body weight",
) {
    Card(
        modifier = modifier
            .padding(all = 5.dp)
            .height(Height)
            .fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))

            .clickable { onClick.invoke() }
            .then(IconbuttonSizeModifier),
        shape = RoundedCornerShape(5.dp),

        elevation = elevation
    )
    {
        Row(
            modifier = Modifier
                .height(20.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color.Cyan, Color(0xFFc3f2ff)),
                        radius = 400f,
                        center = Offset(275f, Float.POSITIVE_INFINITY)
                    )
                ), horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            Icon(
                modifier = modifier
                    .size(70.dp)
                    .align(CenterVertically),
                painter = painterResource(id = com.example.repfluentv2.R.drawable.ic_baseline_published_with_changes_24),
                contentDescription = "Plus Or Minus Icon",
                tint = tint
            )



            Spacer(
                modifier = Modifier
                    .width(20.dp)
                    .padding(top = 10.dp)
            )


            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier
                    .align(CenterVertically)
                    .wrapContentSize()
            ) {
                Text(
                    text = TextTitle,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Text(
                    text = CardBody,
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                modifier = Modifier
                    .width(20.dp)
                    .padding(top = 10.dp)
            )

            Icon(
                modifier = modifier
                    .size(70.dp)
                    .padding(start = 10.dp)
                    .rotate(350f)
                    .align(CenterVertically),
                painter = painterResource(id = com.example.repfluentv2.R.drawable.ic_baseline_arrow_forward_24),
                contentDescription = "Plus Or Minus Icon",
                tint = tint
            )

            Spacer(
                modifier = Modifier
                    .width(20.dp)
                    .padding(top = 10.dp)
            )

            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier
                    .align(CenterVertically)
                    .wrapContentSize()
            ) {
                Text(
                    text = TextTitle,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(CenterHorizontally)
                )

                Text(
                    text = "Latest",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
            }

        }
    }

}


@Composable
fun SmallCard(
    Height: Dp = 90.dp,
    Width: Dp = 150.dp,
    Weight: Float = 1f,
    Colour: Color = Color(0xFFDA8530),
    Colour2: Color = Color(0xFF30CCDA),
    image: Painter = painterResource(id = R.drawable.scales2),
    Title: String,
    IconSize: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var newTitle:String = ""
    if (Title.contains(" ")) {
        Log.d("222", Title)
        newTitle = Title.replace(" ", "\n")
        Log.d("222", Title)
    }
    Card(
        modifier = modifier
            .width(Width)
            .heightIn(Height)
            .padding(all = 5.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .clickable { onClick.invoke() }
            .then(modifier.heightIn(1.dp)),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Colour,
        elevation = 10.dp
    )
    {
        Box(modifier = Modifier
            .wrapContentSize()
            .background(Brush.linearGradient(
                colors = listOf(Colour, Colour2),
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f)
            ))) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier
                        .width(IconSize)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 5.dp)
                )

                Text(text = newTitle,
                    style = MaterialTheme.typography.body1,
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BottomCard(
    modifier: Modifier = Modifier,
    CenterBoxName: String = "View",
    onClick2: () -> Unit, onClick3: () -> Unit, onClick4: () -> Unit,
    image: Painter = painterResource(id = R.drawable.bench),
    Height: Dp = 100.dp,
    Width: Dp = 100.dp,
    SecondButtons: Boolean = false,
    AsynUrl: String = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F07%2F13%2FUltimate-Veggie-Burgers-FT-Recipe-0821.jpg&q=60",
    RadiusColour1: Color = Color(0xffffe1c3),
    RadiusColour2: Color = Color(0xfffba94f),
    elevation: Dp = 10.dp,
    Title: String = "Weight Log",
    CardBody: String = "Log and compare your body weight",


    ) {
    Card(
        modifier = modifier
            .padding(all = 5.dp)
            .height(310.dp)
            .fillMaxWidth()

            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(10.dp))
            .then(IconbuttonSizeModifier), shape = RoundedCornerShape(5.dp),
        elevation = elevation
    )

    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(
                Brush.radialGradient(
                    colors = listOf(RadiusColour1, RadiusColour2),
                    radius = 200f,
                    center = Offset(800f, Float.POSITIVE_INFINITY)
                )
            )
        )

        {
            Text(
                text = Title,
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                modifier = modifier.padding(7.dp),
            )
            val painter = rememberImagePainter(
                data = AsynUrl,
                builder = {

                })

            Image(
                painter = painter,
                contentDescription = "Image",
                modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )


            Spacer(
                modifier = Modifier
                    .width(20.dp)
                    .padding(top = 10.dp)
            )
            if (SecondButtons) {
                ConstraintLayout(
                    modifier = Modifier
                        .align(alignment = CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    val (Button, Button3, Button2) = createRefs()
                    Button(
                        onClick = onClick4,
                        modifier = Modifier
                            .padding(end = 40.dp, start = 10.dp)
                            .constrainAs(Button) { start.linkTo(parent.start) }) {
                        Text(text = "Prev", color = Color.Black)
                    }

                    Button(onClick = onClick2,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(150.dp)
                            .constrainAs(Button3) { start.linkTo(Button.end) })

                    {
                        Text(text = CenterBoxName, color = Color.White)
                    }

                    Button(
                        onClick = onClick3,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .constrainAs(Button2) { end.linkTo(parent.end) }) {
                        Text(text = "Next", color = Color.Black)
                    }
                }
            } else if (!SecondButtons) {
                Button(
                    onClick = onClick2,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(150.dp)
                        .align(alignment = CenterHorizontally)
                )

                {
                    Text(text = "View", color = Color.White)
                }
            }

        }
    }
}

@OptIn(ExperimentalPagerApi::class, DelicateCoroutinesApi::class)
@Composable
fun Content(
    modifier: Modifier = Modifier,
    onClick2: () -> Unit,
    itemsList: ArrayList<String>,
    RadiusColour1: Color = Color(0xffffe1c3),
    RadiusColour2: Color = Color(0xfffba94f),
    elevation: Dp = 10.dp,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Card(
        elevation = 20.dp,
        modifier = Modifier
            .padding(10.dp)
            .width(300.dp)
            .background(color = Color(0xFFDFEAEE))

    ) {
        Column() {
            Text(
                text = "Ingredients",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 20.dp, bottom = 5.dp),
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
            var Ingri = itemsList.chunked(4)

            val pagerState = rememberPagerState()

            HorizontalPager(
                count = Ingri.size,
                contentPadding = PaddingValues(horizontal = 30.dp),
                state = pagerState
            ) { itemsNum ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 5.dp),
                    elevation = 20.dp
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                    ) {
                        val (Button, Button3, Text) = createRefs()
                        Text(
                            text = Ingri[itemsNum].toString().replace("[", "").replace("]", ""),
                            style = androidx.compose.material.MaterialTheme.typography.body1,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, end = 5.dp)
                                .constrainAs(Text) {
                                    start.linkTo(parent.start)
                                    width = Dimension.wrapContent
                                },
                        )
                    }
                }
            }

            androidx.compose.material.Button(
                onClick = onClick2,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                modifier = Modifier

                    .width(70.dp)
                    .padding(end = 5.dp)
                    .align(alignment = Alignment.End)
            )
            {
                Text(text = "back", color = Color.White, fontSize = 10.sp)
            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentScrollView(
    modifier: Modifier = Modifier,
    onClick2: () -> Unit,
    AsynUrl: ArrayList<String> = arrayListOf<String>("http://app.exrx.net/upload/images/5b934d1840cec.jpg",
        "http://app.exrx.net/upload/images/5b934d19808e6.jpg"),
    itemsList: ArrayList<String>,
    Title: String = "",
    RadiusColour1: Color = Color(0xffffe1c3),
    RadiusColour2: Color = Color(0xfffba94f),
    elevation: Dp = 10.dp,
    scope: CoroutineScope,
) {
    Card(
        elevation = 20.dp,
        modifier = Modifier
            .width(400.dp)
            .background(color = Color(0xFFDFEAEE))

    ) {
        Column() {
        Text(text = Title,
            color = Color.Black,
            fontSize = 16.sp, modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 7.dp)
                .fillMaxWidth())


        var Instruc = itemsList.chunked(2)
        Column() {

            val pagerState = rememberPagerState()
            HorizontalPager(
                count = AsynUrl.size,
                state = pagerState
            ) { itemsNum ->
                val painter = rememberImagePainter(
                    data = AsynUrl[itemsNum],
                    builder = {

                    })

            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 5.dp),
                contentScale = ContentScale.FillBounds
            )}

            Card(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(bottom = 5.dp)
                    .height(100.dp),
                elevation = 20.dp
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                ) {
                    val (Image, Button3, Text) = createRefs()
                    LazyColumn() {
                        items(Instruc.size) { item ->
//                            Text(text = Instruc[item].toString().replace("[", "").replace("]", ""),
//                                fontSize = 14.sp,
//                                modifier = modifier.padding(5.dp))

                        Row() {
                           var t = Instruc[item].toString().replace("[", "").replace("]", "")
                            var tChanged:String = ""
                            if (t.startsWith(" ")){
                                tChanged = t.removeRange(0,1)
                            }else{
                                tChanged = t
                            }
                            if (!(tChanged.toString().equals(""))){
                            Text(text = "${item + 1}.",
                                color = Color.Black,
                                fontSize = 20.sp, modifier = Modifier
                                    .padding(start = 10.dp))


                            Text(text = tChanged,
                                color = Color.Black,
                                fontSize = 13.sp, modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 7.dp)
                                    .fillMaxWidth())
                        }}
                    }}
                }
            }
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect {
                    while(true) {
                        yield()
                        delay(3500)
                        tween<Float>(2000)
                        pagerState.animateScrollToPage(
                            page = (pagerState.currentPage + 1) % (pagerState.pageCount))
                    }
                }}
            androidx.compose.material.Button(
                onClick = onClick2,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                modifier = Modifier

                    .width(70.dp)
                    .padding(end = 5.dp)
                    .align(alignment = Alignment.End)
            )
            {
                Text(text = "back", color = Color.White, fontSize = 10.sp)
            }
        }

    }}

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FlipCardContent(
    modifier: Modifier = Modifier,
    onClick2: () -> Unit,
    image: Painter = painterResource(id = R.drawable.bench),
    AsynUrl: String = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F07%2F13%2FUltimate-Veggie-Burgers-FT-Recipe-0821.jpg&q=60",
    RadiusColour1: Color = Color(0xffffe1c3),
    RadiusColour2: Color = Color(0xfffba94f),
    elevation: Dp = 20.dp,
    Title: String = "Weight Log",
) {
    Card(
        modifier = Modifier
            .width(400.dp)
            .padding(end = 5.dp)
            .background(color = Color(0xFFDFEAEE)),
        elevation = elevation
    ) {
        Column(
            modifier = Modifier.background(
                Brush.radialGradient(
                    colors = listOf(RadiusColour1, RadiusColour2),
                    radius = 200f,
                    center = Offset(800f, Float.POSITIVE_INFINITY)
                )
            )
        ) {
            val painter = rememberImagePainter(
                data = AsynUrl,
                builder = {

                })
            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 20.dp),
                contentScale = ContentScale.FillBounds
            )
            ConstraintLayout(
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
            ) {
                val (Button, Button3, Text) = createRefs()
                Text(
                    text = Title,
                    style = androidx.compose.material.MaterialTheme.typography.body1,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp)
                        .constrainAs(Text) {
                            centerHorizontallyTo(parent)
                            width = Dimension.wrapContent
                        },
                )
                androidx.compose.material.Button(onClick = onClick2,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                    modifier = Modifier
                        .constrainAs(Button) {
                            centerHorizontallyTo(Text)
                            top.linkTo(Text.bottom)
                        }
                        .width(120.dp)
                        .padding(end = 5.dp))
                {
                    Text(text = "Info", color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlipCard(
    cardFace: CardFace, elevation: Dp = 20.dp,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},

    ) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    Card(
        elevation = elevation, backgroundColor = Color.Blue.copy(alpha = 0.0f),
        modifier = modifier.padding(bottom = 10.dp).graphicsLayer {
            rotationY = rotation.value
            cameraDistance = 24f * density
        },
    ) {
        Box(
        ) {
            if (rotation.value <= 90f) {
                front()
            } else {
                Box(
                    Modifier.padding(horizontal = 10.dp).background(color = Color.Black.copy(alpha = 0.1f))
                        .graphicsLayer{
                            rotationY = 180f
                        },
                ) {
                    back()
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RepTextField(
    ColumnModfier: Modifier = Modifier,
    show: Boolean = true,
    Error: Boolean? = false,
    ErrorMessage: MutableState<String>? = null,
    IsPass: Boolean = false,
    NoCaption: Boolean = false,
    cardModfier: Modifier = Modifier,
    captionText: String = "Email",
    NumLetters: Int = 30,
    TextLook: VisualTransformation = VisualTransformation.None,
    KeyBoardType: KeyboardType = KeyboardType.Email,
    ErrorColor: Color = Color.Red,
    SuccessColor: Color = Color.Black,
    Scope: CoroutineScope = rememberCoroutineScope(),
    FocuseManager: FocusManager = LocalFocusManager.current,
    bringIntoViewRequester: BringIntoViewRequester = BringIntoViewRequester(),
    TextFieldString: String,
    CardWidth: Dp = 400.dp,
    GottenValue: String = "",
    onValueChanged: (String) -> Unit,
    TextFieldStringCount: MutableState<Int>,
) {

    var pass by remember { mutableStateOf(false) }
    Column(ColumnModfier.padding(bottom = 5.dp)) {

        if (!NoCaption) {
            Text(
                text = captionText, color = Color.Black, fontSize = 13.sp,
                fontFamily = aaargh, modifier = Modifier
                    .padding(start = 5.dp)
                    .align(Start)
            )
        }

        Card(elevation = 10.dp, modifier = cardModfier.size(CardWidth, 50.dp),
            shape = RoundedCornerShape(8.dp))

        {
            TextField(modifier = Modifier
                .size(200.dp)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        Scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .bringIntoViewRequester(bringIntoViewRequester),
                value = if (GottenValue.isEmpty()) {
                    TextFieldString
                } else {
                    GottenValue
                },
                onValueChange = onValueChanged,
                singleLine = true,
                textStyle = androidx.compose.material.MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyBoardType,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions.Default,
                shape = RoundedCornerShape(8.dp),
                visualTransformation =
                if (!show) {
                    if (pass) {
                        VisualTransformation.None
                    } else {
                        TextLook
                    }
                } else {
                    TextLook
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFE9EAEB),
                    cursorColor = Color.Gray,
                    disabledLabelColor = Color(0xFF98C6CC),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    if (IsPass) {
                        IconButton(onClick = {
                            pass = !pass
                        }) {
                            if (pass) {
                                Icon(imageVector = Icons.Default.ArrowForward, "Show")
                            } else {
                                Icon(imageVector = Icons.Default.ArrowBack, "Hide")
                            }
                        }
                    }
                }
            )
        }
        if (show) {
            Text(
                text = TextFieldString.length.toString() + "/" + NumLetters.toString(),
                color = if (
                    TextFieldString.length < NumLetters
                ) {
                    SuccessColor
                } else {
                    ErrorColor
                },
                fontSize = 15.sp, fontFamily = aaargh, modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 5.dp)
            )
        }
        if (Error == true)
            if (ErrorMessage != null) {
                Text(
                    text = ErrorMessage.value.toString(),
                    color = Color.Red,
                    fontSize = 15.sp, fontFamily = aaargh, modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 5.dp)
                )
            }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Button1(
    modifier: Modifier = Modifier,
    ButtonWidth: Dp = 150.dp,
    onClick: () -> Unit,
    Selected: Boolean = false,
    backgroundColor: Color = Color(0xFFBFEFFB),
    ButtonText: String,
) {
    var color by remember { mutableStateOf(backgroundColor) }
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .widthIn(min = ButtonWidth)
            .wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color, disabledBackgroundColor = Color(0xFFBFEFFB)
        ),
    )
    {
        Text(text = ButtonText,
            Modifier
                .wrapContentHeight()
                .align(CenterVertically), fontFamily = aaargh
        )
    }
}

@Composable
fun ButtonGroup(
    modifier: Modifier = Modifier,
    ButtonWidth: Dp = 80.dp,
    CurrentButton: String,
    onClick: () -> Unit,
    ButtonTitles: List<String>,
    Selected: Boolean = false,
    backgroundColor: Color = Color(0xFFBFEFFB),
    ButtonText: String,
    RowModifier: Modifier = modifier,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = RowModifier
    ) {
        Button2(
            onClick = onClick,
            ButtonText = ButtonTitles[0],
            CurrentButton = CurrentButton

        )
        Button2(
            onClick = onClick,
            ButtonText = ButtonTitles[1],
            CurrentButton = CurrentButton
        )
        Button2(
            onClick = onClick,
            ButtonText = ButtonTitles[2],
            CurrentButton = CurrentButton
        )
    }
}

@Composable
fun Button2(
    modifier: Modifier = Modifier,
    ButtonWidth: Dp = 80.dp,
    CurrentButton: String,
    onClick: () -> Unit,
    Selected: Boolean = false,
    backgroundColor: Color = Color(0xFFBFEFFB),
    ButtonText: String,
) {
    var color by remember { mutableStateOf(backgroundColor) }
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .widthIn(min = ButtonWidth)
            .wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (CurrentButton.equals(ButtonText)) {
                color
            } else {
                Color(0xFFDFF6FC)
            }, disabledBackgroundColor = Color(0xFFBFEFFB)
        ),
    )
    {
        Text(text = ButtonText,
            Modifier
                .wrapContentHeight()
                .align(CenterVertically), fontFamily = aaargh
        )
    }
}

@Composable
fun DropDownMenu(
    TextFieldValue: String,
    id: Int = com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down,
    IconClickable: () -> Unit,
    OnSizeFunc: (LayoutCoordinates) -> Unit,
    EnabledChange: Boolean = false,
    EnabledChangedFunc: () -> Unit,
    Size: Float,
    items: List<String>,
    DropDownonSelectedIndex: (Int) -> Unit,
) {
    Box {
        OutlinedTextField(value = TextFieldValue,
            onValueChange = {},
            textStyle = androidx.compose.material.MaterialTheme.typography.body1.copy(fontSize = 15.sp),
            label = { Text(text = "label", fontFamily = aaargh, fontSize = 12.sp) },
            enabled = false,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = id),
                    contentDescription = null,
                    Modifier.clickable {
                        IconClickable
                    }
                )
            },
            modifier = Modifier
                .width(150.dp)
                .heightIn(60.dp)
                .onGloballyPositioned
                {
                    OnSizeFunc
                }
        )
        DropdownMenu(expanded = EnabledChange,
            onDismissRequest = {
                EnabledChangedFunc
            },
            modifier = Modifier.width(with(LocalDensity.current) { Size.toDp() })
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick =
                    {
                        DropDownonSelectedIndex(index)
                        EnabledChangedFunc
                    })
                {
                    Text(text = s, fontFamily = aaargh, fontSize = 12.sp)
                }
            }
        }

    }
}
