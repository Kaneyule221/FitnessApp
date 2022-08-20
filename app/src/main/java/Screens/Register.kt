@file:JvmName("RegisterKt")

package Screens

import Screens.ui.theme.RepfluentV2Theme
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myapplication.Adapter_and_Singletons.ExerciseFirebaseInfo
import com.example.repfluentv2.CheckUserInfo
import com.example.repfluentv2.ui.theme.aaargh
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import components.*
import navigation.CreateAccount
import org.json.JSONObject
import userinfo.Userinformation
import java.text.DecimalFormat
import kotlin.math.roundToInt

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepfluentV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Card(
        modifier = Modifier
            .padding(all = 5.dp)
            .height(300.dp)
            .widthIn(min = 200.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .then(IconbuttonSizeModifier),
        shape = RoundedCornerShape(5.dp),
    ) {
        Column(

            modifier = Modifier
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color.Yellow, Color.Green),
                        radius = 200f,
                        center = Offset(800f, Float.POSITIVE_INFINITY)
                    )
                )
        ) {
            val painter = rememberImagePainter(
                data = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F07%2F13%2FUltimate-Veggie-Burgers-FT-Recipe-0821.jpg&q=60",
                builder = {

                })

            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )


        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Greetings1(s: String) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(300.dp)
            .background(color = Color(0xFFDFEAEE))

    ) {
        Column {
            Text(
                text = "Ingredients",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 20.dp, bottom = 5.dp),
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
            HorizontalPager(count = 4, contentPadding = PaddingValues(horizontal = 30.dp)) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 5.dp), elevation = 20.dp
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                    ) {
                        val (Button, Button3, Text) = createRefs()
                        Text(
                            text = "White Cress Salad with Southern Fried chickenssssssssssssssss",
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
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1d1d1d)),
                modifier = Modifier
//                        .constrainAs(Button) {
//                            end.linkTo(parent.end)
//                            top.linkTo(Text.bottom)
//                        }
                    .width(60.dp)
                    .padding(end = 5.dp)
                    .align(alignment = End)
            )
            {
                Text(text = "Info", color = Color.White, fontSize = 10.sp)
            }
        }

    }
}


@Composable
fun Login(
    navController: NavController?,
    EmailM: MutableState<String>,
    EmailCountM: MutableState<Int>,
    PasswordM: MutableState<String>,
    PasswordCountM: MutableState<Int>,
    TextViewModel: ViewModelS,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 125.dp)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Repfluent", color = Color.Black, fontSize = 50.sp,
            fontFamily = aaargh,
        )

        RepTextField(
            TextFieldString = TextViewModel.EmailM,
            TextFieldStringCount = mutableStateOf(TextViewModel.EmailCountM), NumLetters = 30,
            captionText = "Email",
            onValueChanged = { TextViewModel.onTextChanged(it, 30) },
            ErrorColor = TextViewModel.ErrorColor
//            NumDiff = {TextViewModel.onPasswordCountChanged()}
        )
        RepTextField(
            TextFieldString = TextViewModel.PasswordM,
            TextFieldStringCount = mutableStateOf(TextViewModel.PasswordCountM),
            captionText = "Password",
            KeyBoardType = KeyboardType.Password,
            NumLetters = 20,
            TextLook = PasswordVisualTransformation(),
            show = false,
            IsPass = true,
            onValueChanged = { TextViewModel.onPasswordChanged(it) }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {

            Button1(onClick = {
                LoginFunc(
                    TextViewModel.EmailM,TextViewModel.PasswordM,TextViewModel, navController )

            }, ButtonText = "log in")
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Button1(onClick = {

                navController?.navigate(CreateAccount.Signup.withargs("?Email=${TextViewModel.EmailM}/?Email1=${TextViewModel.EmailM}"))

            }, ButtonText = "Sign Up")


        }
        if (TextViewModel.LoginError){
            TextViewModel.LoginError = false
            val text = "Email or password is empty/incorrect"
            val duration = Toast.LENGTH_SHORT
            val context = LocalContext.current
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

//        if(TextViewModel.LoginSuccess){
//            MainScreen("Name: String",TextViewModel)
//        }


    }
}

fun LoginFunc(Email:String, Password:String,TextViewModel: ViewModelS, navController: NavController?){

    var mAuth: FirebaseAuth? = null
    mAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val collectionReference = db.collection("users")
    if (Email.isNotEmpty() && Password.isNotEmpty()){
        mAuth.signInWithEmailAndPassword(Email,Password).addOnSuccessListener {
            var user: FirebaseUser? = mAuth.currentUser
            var UserId:String = user!!.uid
            collectionReference.whereEqualTo("UserId",UserId).addSnapshotListener(){querySnapshot,Firebase ->
                if (!(querySnapshot!!.isEmpty)){
                    querySnapshot.forEach(){
                        val Userinformation: Userinformation? = Userinformation.instance
                        val id: String = it.id as String
                        Userinformation!!.uDocumentId = id
                        Userinformation.userID = it["UserId"] as String?
                        Userinformation.goalWeight =  it["Goal Weight"] as String?
                        Userinformation.username =  it["Username"] as String?
                        Userinformation.weight =  it["Current Weight"] as String?
                        Userinformation.age =  it["Age"] as String?
                        Userinformation.height =  it["Height"] as String?
                        Userinformation.totalMeals =  it["TotalMeals"] as String?
                        Userinformation.setCalories(it["Calories"] as String?)
                        Userinformation.setCarbs(it["Carbs"] as String?)
                        Userinformation.setFat(it["Fat"] as String?)
                        Userinformation.setProtein(it["Protein"] as String?)
                        Userinformation.weightAim = it["WeightGoal"] as String?
                        Log.d("User", Userinformation.username.toString())
                        TextViewModel.LoginSuccess = true
                    }

                }
            }
        }.addOnFailureListener {
            TextViewModel.LoginError = true
        }
    }else{
        TextViewModel.LoginError = true
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUp(
    LogEmail: String,
    navController: NavController?,
    ViewModel: SignupViewModel,
    LoginViewModel: ViewModelS = remember {
        ViewModelS()
    }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 125.dp)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {

        val ErrorMessage = remember { mutableStateOf("") }
        var ShowError by remember { mutableStateOf(false) }
        var Error by remember { mutableStateOf(false) }
        val UsernameCount = remember { mutableStateOf(0) }
        val EmailCount = remember { mutableStateOf(0) }
        val PasswordCount = remember { mutableStateOf(0) }


        val coroutineScope = rememberCoroutineScope()
        val focusManager = LocalFocusManager.current


        var passwordVisibility by remember { mutableStateOf(false) }


        Text(
            text = "Repfluent", color = Color.Black, fontSize = 50.sp,
            fontFamily = aaargh,
        )

        RepTextField(
            TextFieldString = LoginViewModel.EmailM,
            TextFieldStringCount = EmailCount, NumLetters = 40,
            captionText = "Email",
            onValueChanged = { LoginViewModel.EmailM = it }
        )

        RepTextField(
            TextFieldString = ViewModel.Username,
            TextFieldStringCount = UsernameCount, NumLetters = 40,
            captionText = "Username",
            onValueChanged = { ViewModel.UsernameChanged(it, 40) }
        )

        RepTextField(
            TextFieldString = ViewModel.PasswordM,
            TextFieldStringCount = PasswordCount,
            captionText = "Password",
            KeyBoardType = KeyboardType.Password,
            NumLetters = 20,
            TextLook = PasswordVisualTransformation(),
            show = false,
            IsPass = true, Error = Error, ErrorMessage = ErrorMessage,
            onValueChanged = { ViewModel.onPasswordChanged(it) },
            bringIntoViewRequester = ViewModel.bringIntoViewRequester
        )



        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {

            Button1(onClick = {
                if (CheckUserInfo(
                        LoginViewModel.EmailM,
                        ViewModel.Username,
                        ViewModel.PasswordM
                    ) == "true"
                ) {
                    navController?.navigate(
                        CreateAccount.UserDetails.withargs(
                            "?Email=${LoginViewModel.EmailM}" +
                                    "/?Username=${ViewModel.Username}/?Password=${ViewModel.PasswordM}"
                        )
                    ) { }
                } else {
                    ErrorMessage.value = CheckUserInfo(
                        LoginViewModel.EmailM,
                        ViewModel.Username,
                        ViewModel.PasswordM
                    )
                    Log.d("SignUp", ErrorMessage.value)
                    Error = true
                }
            }, ButtonText = "Next", ButtonWidth = 200.dp)

        }

    }
}


@Composable
fun UserDetails(
    GottenEmail: String = "",
    Username: String = "",
    password: String = "",
    navController: NavController? = rememberNavController(),
    UserInViewModel: UserInfoViewModel = UserInfoViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp),

        ) {
        val currentUser: FirebaseUser? = null
        val db = FirebaseFirestore.getInstance()
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        Text(
            text = "Repfluent", color = Color.Black, fontSize = 50.sp,
            fontFamily = aaargh, modifier = Modifier.align(CenterHorizontally)
        )

        val stateHolder = remeberExposedMenuState(hey = "s")

        RepTextField(
            KeyBoardType = KeyboardType.Number,
            TextFieldString = UserInViewModel.Weight,
            show = false,
            cardModfier = Modifier.padding(bottom = 30.dp),
            TextFieldStringCount = mutableStateOf(UserInViewModel.Weight.length),
            NumLetters = 4,
            captionText = "Weight(kg)",
            onValueChanged = { UserInViewModel.onWeightChanged(it, 3) }
        )

        RepTextField(
            KeyBoardType = KeyboardType.Number,
            TextFieldString = UserInViewModel.Height,
            show = false,
            cardModfier = Modifier.padding(bottom = 20.dp),
            TextFieldStringCount = mutableStateOf(UserInViewModel.Height.length),
            NumLetters = 3,
            captionText = "Height(cm)",
            onValueChanged = { UserInViewModel.onHeightChanged(it, 3) }
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            RepTextField(CardWidth = 100.dp,
                captionText = "Age(y)",
                TextFieldString = UserInViewModel.Age.toString(),
                TextFieldStringCount = mutableStateOf(UserInViewModel.Age.toString().length),
                KeyBoardType = KeyboardType.Number, show = false,
                onValueChanged = { UserInViewModel.AgeChanged(it, 2) }
            )



            RepTextField(CardWidth = 100.dp,
                captionText = "Weight Goal",
                TextFieldString = UserInViewModel.WeightGoal,
                TextFieldStringCount = mutableStateOf(UserInViewModel.WeightGoal.length),
                KeyBoardType = KeyboardType.Number, show = false,
                onValueChanged = { UserInViewModel.onWeightGoalChanged(it, 3) }
            )
        }




        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Goal",
                fontFamily = aaargh,
                fontSize = 15.sp,
                modifier = Modifier.widthIn(5.dp)
            )
            val ButtonGroupNames = arrayListOf<String>("Lose", "Maintain", "Gain")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp),
            )
            {
                items(ButtonGroupNames.size) { model ->
                    Button2(
                        onClick = { UserInViewModel.onButtonGroupGoalChange(ButtonGroupNames[model]) },
                        ButtonText = ButtonGroupNames[model],
                        CurrentButton = UserInViewModel.ButtonGroupGoalVal

                    )
                }
            }
            Text(
                text = "Change Intensity",
                fontFamily = aaargh,
                fontSize = 15.sp,
                modifier = Modifier
                    .widthIn(5.dp)
                    .padding(top = 5.dp)
            )
            val ButtonGroupNames2 = arrayListOf<String>("Mild", "Medium", "Faster")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp),
            )
            {
                items(ButtonGroupNames2.size) { model ->
                    Button2(
                        onClick = { UserInViewModel.onButtonGroupChangeVal(ButtonGroupNames2[model]) },
                        ButtonText = ButtonGroupNames2[model],
                        CurrentButton = UserInViewModel.ButtonGroupChangeVal

                    )
                }
            }
            Text(
                text = "Cardio Per Week",
                fontFamily = aaargh,
                fontSize = 15.sp,
                modifier = Modifier
                    .widthIn(5.dp)
                    .padding(top = 5.dp)
            )
            val ButtonGroupNames3 =
                arrayListOf<String>("None", "1-3 (Days)", "4-5 (Days)", "7 (Days)")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp),
            )
            {
                items(ButtonGroupNames3.size) { model ->
                    Button2(
                        onClick = { UserInViewModel.onButtonGroupActivityVal(ButtonGroupNames3[model]) },
                        ButtonText = ButtonGroupNames3[model],
                        CurrentButton = UserInViewModel.ButtonGroupActivityVal

                    )
                }
            }

            Button1(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp),
                onClick = { UserInViewModel.LoadBoolean = true },
                ButtonText = "Sign Up",
            )

        }

        if (UserInViewModel.LoadBoolean) {
            if (UserInViewModel.Weight.isNotEmpty() && UserInViewModel.Height.isNotEmpty() && UserInViewModel.Age.isNotEmpty()
                && UserInViewModel.WeightGoal.isNotEmpty() && UserInViewModel.ButtonGroupGoalVal.isNotEmpty()
                && UserInViewModel.ButtonGroupChangeVal.isNotEmpty() && UserInViewModel.ButtonGroupActivityVal.isNotEmpty()
            ) {
                if (UserInViewModel.Weight.toInt() > UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Lose"
                    || UserInViewModel.Weight.toInt() < UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Gain" ||
                    UserInViewModel.Weight.toInt() == UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Maintain"
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 10.dp)
                    )

                    GetUserInformation(
                        gottenEmail = GottenEmail,
                        password = password,
                        firebaseAuth = firebaseAuth,
                        UserInViewModel = UserInViewModel,
                        currentUser = currentUser,
                        Age = UserInViewModel.Age,
                        Weight = UserInViewModel.Weight,
                        Height = UserInViewModel.Height,
                        Username = Username,
                        CardioPerWeek = UserInViewModel.ButtonGroupActivityVal,
                        WeightGoal = UserInViewModel.ButtonGroupGoalVal,
                        GoalWeightNum = UserInViewModel.WeightGoal,
                        ChangeIntensity = UserInViewModel.ButtonGroupChangeVal,
                        navController = navController
                    )
                    UserInViewModel.LoadBoolean = false


                } else if (UserInViewModel.Weight.toInt() < UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Lose" ||
                    UserInViewModel.Weight.toInt() == UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Lose"
                ) {
                    UserInViewModel.LoadBoolean = false
                    val text =
                        "Goal cant be \"lose\" when weight is less than or equal to goal weight"
                    val duration = Toast.LENGTH_LONG
                    val context = LocalContext.current
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                } else if (UserInViewModel.Weight.toInt() > UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Gain" ||
                    UserInViewModel.Weight.toInt() == UserInViewModel.WeightGoal.toInt() && UserInViewModel.ButtonGroupGoalVal == "Gain"
                ) {
                    UserInViewModel.LoadBoolean = false
                    val text =
                        "Goal cant be \"Gain\" when weight is more than or equal to goal weight"
                    val duration = Toast.LENGTH_LONG
                    val context = LocalContext.current
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            } else {
                UserInViewModel.LoadBoolean = false
                val text = "No Empty Fields"
                val duration = Toast.LENGTH_SHORT
                val context = LocalContext.current
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }

        }
    }

}

@Composable
fun GetUserInformation(
    gottenEmail: String,
    password: String,
    firebaseAuth: FirebaseAuth,
    UserInViewModel: UserInfoViewModel,
    Weight: String,
    Height: String,
    Age: String,
    WeightGoal: String,
    currentUser: FirebaseUser?,
    Username: String,
    GoalWeightNum: String,
    ChangeIntensity: String,
    navController: NavController?,
    CardioPerWeek: String
): String {
    var userOBjString: String = ""
    val foodInfosArrayList = ArrayList<ExerciseFirebaseInfo>()
    UserInViewModel.LoadBoolean = false
    //TAKE them to the second signup page
//        var CurrentuserID: String = firebaseAuth.currentUser!!.uid


    val MacrosNmeals =
        CalculateMacros_Meal(WeightGoal, Weight, Height, Age, ChangeIntensity, CardioPerWeek)
    Log.d("Macros", MacrosNmeals.toString())
    //create a user map
    val userOBJ: MutableMap<String, String> = HashMap()
    userOBJ["Calories"] = MacrosNmeals["Calories"].toString() + "kcal"
    userOBJ["Carbs"] = MacrosNmeals["Carbs"].toString() + "g"
    userOBJ["Protein"] = MacrosNmeals["Protein"].toString() + "g"
    userOBJ["Fat"] = MacrosNmeals["Fat"].toString() + "g"

    userOBJ["Age"] = Age + "y"
    userOBJ["Height"] = Height + "cm"
    userOBJ["Weight"] = Weight + "kg"
    userOBJ["Goal"] = WeightGoal

    userOBJ["GoalWeight"] = GoalWeightNum + "kg"
    userOBJ["TotalMeals"] = MacrosNmeals["MealNum"].toString()
    userOBJ["Estimated Weight Loss/Gain per week"] =
        MacrosNmeals["WeightLossPerWeek"].toString() + " kg"
    userOBJ["ActivityLevel"] = MacrosNmeals["Activity Level"].toString()


    userOBJ["Username"] = Username
    userOBJ["Email"] = gottenEmail
    userOBJ["Password"] = password

    userOBjString = JSONObject((userOBJ as Map<String, String>?)!!).toString()

    if (userOBJ["Calories"]!!.replace("kcal", "").toInt() > 1600) {
        navController?.navigate(CreateAccount.Stats.withargs("?UserInfo=${userOBjString}"))
    } else {
        val text = "Contact your doctor, Calculated calories to low"
        val duration = Toast.LENGTH_SHORT
        val context = LocalContext.current
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
    return userOBjString
}

@Composable
fun stats(
    UserInfo: String = "",
    navController: NavController? = rememberNavController(),
    StatsViewModel: StatsViewModel
) {
    Log.d("UserObj", UserInfo)

    var JsonUserInfor = JSONObject(UserInfo)

    val authStateListener: FirebaseAuth.AuthStateListener? = null
    val currentUser: FirebaseUser? = null
    val db = FirebaseFirestore.getInstance()
    val collectionReference = db.collection("users")
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 2f), 20f)
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp),

        ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 10.dp),
            text = "Repfluent", color = Color.Black, fontSize = 50.sp,
            fontFamily = aaargh,
        )
        Card(
            elevation = 30.dp,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xffbfeffb), Color(0xfff1fafd)),
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
                    .fillMaxWidth()
            )
            {
                Text(
                    text = "Stats and Macros", fontFamily = aaargh,
                    fontSize = 21.sp, modifier = Modifier.padding(top = 10.dp, start = 15.dp)
                )

                DrawDottedLine(
                    PathEffect = pathEffect,
                    modifier = Modifier.align(CenterHorizontally)
                )
                val ItemsNameArray = arrayListOf(
                    "Calories",
                    "Carbs",
                    "Protein",
                    "Fat",
                    "Age",
                    "Height",
                    "Weight",
                    "Goal",
                    "GoalWeight",
                    "TotalMeals",
                    "ActivityLevel",
                    "Estimated Weight Loss/Gain per week"
                )
                for (key1 in JsonUserInfor.keys()) {

                    ItemsNameArray.add(key1)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, top = 10.dp)
                )
                {
                    items(4) { model ->
                        StatsInfo(
                            JsonUserInfor[ItemsNameArray[model]].toString(),
                            "",
                            ItemsNameArray.subList(0, 4)[model]
                        )
                    }
                }
                DrawDottedLine(
                    PathEffect = pathEffect,
                    modifier = Modifier.align(CenterHorizontally)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, top = 10.dp)
                )
                {
                    items(4) { model ->
                        StatsInfo(
                            JsonUserInfor[ItemsNameArray.subList(4, 8)[model]].toString(),
                            "",
                            ItemsNameArray.subList(4, 8)[model]
                        )
                    }

                }
                DrawDottedLine(
                    PathEffect = pathEffect,
                    modifier = Modifier.align(CenterHorizontally)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, top = 10.dp)
                )
                {
                    items(3) { model ->
                        StatsInfo(
                            JsonUserInfor[ItemsNameArray.subList(8, 11)[model]].toString(),
                            "",
                            ItemsNameArray.subList(8, 11)[model]
                        )
                    }

                }


                if (!JsonUserInfor["Estimated Weight Loss/Gain per week"].equals("0 kg")) {


                    DrawDottedLine(
                        PathEffect = pathEffect,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp, top = 10.dp)
                            .align(CenterHorizontally)
                            .wrapContentWidth()
                    ) {
                        val (Text, Text2, Text3) = createRefs()
                        var ChangeperWeek: Float = 0f
                        if (JsonUserInfor["Estimated Weight Loss/Gain per week"] != 0) {
                            ChangeperWeek =
                                JsonUserInfor["Estimated Weight Loss/Gain per week"].toString()
                                    .replace(" kg", "").toFloat() / 100
                        }

                        Text(text = ChangeperWeek.toString(), fontFamily = aaargh,
                            fontSize = 20.sp, modifier = Modifier
                                .padding()
                                .constrainAs(Text) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })

                        Text(text = "Estimated Weight Change per week", fontFamily = aaargh,
                            fontSize = 15.sp, modifier = Modifier
                                .padding()
                                .constrainAs(Text2) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(Text.bottom)
                                })
                    }
                }
                Card(
                    elevation = 30.dp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color(0xffbfeffb), Color(0xfff1fafd)),
                                    start = Offset(0f, Float.POSITIVE_INFINITY),
                                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                                )
                            )
                            .fillMaxWidth()
                    )

                    {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp, top = 10.dp)
                                .align(CenterHorizontally)
                                .wrapContentWidth()
                        ) {
                            val (Text, Text2, Text3) = createRefs()
                            val WeightChangeMessage =
                                if (JsonUserInfor["Estimated Weight Loss/Gain per week"].equals("0 kg")) {
                                    "Following these macros you should maintain your weight"
                                } else {
                                    "Following these macros you should reach your goal in: "
                                }
                            val sixmonthweight =
                                if (JsonUserInfor["Goal"].toString() == "Gain") {
                                    ((JsonUserInfor["GoalWeight"].toString()
                                        .replace("kg", "")
                                        .toInt() - JsonUserInfor["Weight"].toString()
                                        .replace("kg", "")
                                        .toInt()) / JsonUserInfor["Estimated Weight Loss/Gain per week"].toString()
                                        .replace(" kg", "").toFloat() * 100 / 4).roundToInt()


                                } else if (JsonUserInfor["Goal"].toString() == "Lose") {
                                    ((JsonUserInfor["GoalWeight"].toString()
                                        .replace("kg", "")
                                        .toInt() - JsonUserInfor["Goal"].toString()
                                        .replace("kg", "")
                                        .toInt()) / JsonUserInfor["Estimated Weight Loss/Gain per week"].toString()
                                        .replace(" kg", "").toFloat() * 100 / 4).roundToInt()
                                } else {
                                    0
                                }

                            Log.d("Val", sixmonthweight.toString())
                            Text(text = WeightChangeMessage,
                                fontFamily = aaargh,
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding()
                                    .constrainAs(Text) {
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    })

                            if (!JsonUserInfor["Estimated Weight Loss/Gain per week"].equals("0 kg")) {
                                Text(text = "$sixmonthweight Months", fontFamily = aaargh,
                                    fontSize = 15.sp, modifier = Modifier
                                        .padding(top = 10.dp)
                                        .constrainAs(Text2) {
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            top.linkTo(Text.bottom)
                                        })
                            }
                        }

                    }
                }
            }
        }
        Button1(
            onClick =
            {
                CreateAccount(firebaseAuth, JsonUserInfor,StatsViewModel)
            }, ButtonText = "Confirm Details", modifier = Modifier.align(CenterHorizontally)
        )
        if (StatsViewModel.LoadToast){
            val text = "Email in use"
            val duration = Toast.LENGTH_SHORT
            val context = LocalContext.current
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            StatsViewModel.LoadToast = false
        }
    }
}


fun CreateAccount(
    firebaseAuth: FirebaseAuth,
    jsonObject: JSONObject,
    StatsViewModel:StatsViewModel
) {

    jsonObject["Password"]
    val db = FirebaseFirestore.getInstance()
    val collectionReference: CollectionReference = db.collection("users")
    firebaseAuth.createUserWithEmailAndPassword(jsonObject["Email"].toString(), jsonObject["Password"].toString()).addOnSuccessListener()
    {

        //TAKE them to the second signup page
        val currentUser = firebaseAuth.currentUser
        //create a user map
        val userOBJ: MutableMap<String, String> = java.util.HashMap()
        userOBJ["UserId"] = currentUser!!.uid
        userOBJ["Username"] = jsonObject["Username"].toString()
        userOBJ["Age"] = jsonObject["Age"].toString()
        userOBJ["Goal"] = jsonObject["Goal"].toString()
        userOBJ["Height"] = jsonObject["Height"].toString()
        userOBJ["Current Weight"] = jsonObject["Weight"].toString()
        userOBJ["Goal Weight"] = jsonObject["GoalWeight"].toString()
        userOBJ["TotalMeals"] = jsonObject["TotalMeals"].toString()
        userOBJ["Calories"] = jsonObject["Calories"].toString()
        userOBJ["Carbs"] = jsonObject["Carbs"].toString()
        userOBJ["Protein"] = jsonObject["Protein"].toString()
        userOBJ["Fat"] = jsonObject["Fat"].toString()
        collectionReference.add(userOBJ).addOnSuccessListener { dest -> dest.get().addOnCompleteListener()
        {
            val Userinformation: Userinformation? = Userinformation.instance
            val id: String = it.result.id
            Userinformation!!.uDocumentId = id
            Userinformation.userID = currentUser.uid
            Userinformation.goalWeight = it.result.getString("Goal Weight")
            Userinformation.username = it.result.getString("Username")
            Userinformation.weight = it.result.get("Current Weight").toString()
            Userinformation.age = it.result.getString("Age")
            Userinformation.height = it.result.getString("Height")
//            Userinformation.bmi = it.result.getString("Bmi")
            Userinformation.totalMeals = it.result.getString("TotalMeals")
            Userinformation.setCalories(it.result.getString("Calories"))
            Userinformation.setCarbs(it.result.getString("Carbs"))
            Userinformation.setFat(it.result.getString("Fat"))
            Userinformation.setProtein(it.result.getString("Protein"))
            Userinformation.weightAim = it.result.getString("WeightGoal")

//            Log.d("UserInfo",Userinformation.username!!)
        } }
    }.addOnFailureListener {
    StatsViewModel.LoadToast = true
    }
}



//fun Toast(){
//    val text = "No Empty Fields"
//    val duration = Toast.LENGTH_SHORT
//    val context = LocalContext.current
//    val toast = Toast.makeText(context, text, duration)
//    toast.show()
//}



@Composable
private fun StatsInfo(
    MainValue: String,
    Unit: String,
    Label: String,

    ) {

    ConstraintLayout(modifier = Modifier.padding(top = 5.dp, end = 10.dp)) {
        val (Text, Text2, Text3) = createRefs()
        Text(text = MainValue, fontFamily = aaargh,
            fontSize = 20.sp, modifier = Modifier
                .padding()
                .constrainAs(Text)
                { start.linkTo(parent.start) })

        Text(text = Unit, fontFamily = aaargh,
            fontSize = 10.sp, modifier = Modifier
                .padding(start = 2.dp)
                .constrainAs(Text2)
                {
                    start.linkTo(Text.end)
                    bottom.linkTo(Text.bottom)

                })

        Text(text = Label, fontFamily = aaargh,
            fontSize = 15.sp, modifier = Modifier
                .padding()
                .widthIn(1.dp)
                .constrainAs(Text3)
                {
                    top.linkTo(Text.bottom)
                    start.linkTo(Text.start)

                })
    }
}

@Composable
fun DrawDottedLine(PathEffect: PathEffect, modifier: Modifier) {

    Canvas(
        modifier = modifier
            .width(320.dp)
            .height(1.dp)
    ) {
        drawLine(
            color = Color.Black, start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            3f,
            pathEffect = PathEffect
        )

    }
}




fun CalculateBmi(Weight: String, Height: String): String {

    var WeightBmi = Weight.toInt()
    var HeightBmi = Height.toFloat()

    val Cal1: Float = WeightBmi / (HeightBmi * HeightBmi)
    val value1 = DecimalFormat("#.#")
    val bmi = value1.format(Cal1.toDouble())

    return bmi
}

fun CalculateMacros_Meal(
    WeightGoal: String,
    Weight: String,
    Height: String,
    Age: String,
    ChangeIntensity: String,
    CardioPerWeek: String

): MutableMap<String, String> {
    val Calories1 = 10 * Weight.toFloat()
    val Calories2: Double = (Height.toFloat() * 6.25)
    val Calories3 = (5 * Age.toFloat() + 5).toDouble()
    var CaloriesFinalBefore = (Calories1 + Calories2 - Calories3) * 1.2
    var CaloriesFinal = (Calories1 + Calories2 - Calories3) * 1.2
    var WeightlossPerWeek: Float = 0f
    val ButtonGroupNames3 = arrayListOf<String>("None", "1-3 (Days)", "4-5 (Days)", "7 (Days)")
    var ActivtyLevel = ""

    var proteinVal = 0.4f
    var CarbsValue = 0.35f
    var FatValue = 0.35f

    Log.d("COMEON", CaloriesFinal.toString())
    when (WeightGoal) {
        "Lose" -> {
            when (CardioPerWeek) {
                "None" -> {
                    CaloriesFinal -= 0
                    ActivtyLevel = "Base Rate"

                    proteinVal = 0.40F
                    CarbsValue = 0.30f
                    FatValue = 0.3f
                }
                "1-3 (Days)" -> {
                    CaloriesFinal += 400
                    ActivtyLevel = "Light"

                    proteinVal = 0.38F
                    CarbsValue = 0.32f
                    FatValue = 0.30f
                }

                "4-5 (Days)" -> {
                    CaloriesFinal += 600
                    ActivtyLevel = "Moderate"

                    proteinVal = 0.31F
                    CarbsValue = 0.38f
                    FatValue = 0.3f
                }

                "7 (Days)" -> {
                    CaloriesFinal += 1400
                    ActivtyLevel = "Very Active"

                    proteinVal = 0.28F
                    CarbsValue = 0.41f
                    FatValue = 0.3f
                }
            }
        }
        "Gain" -> {
            when (CardioPerWeek) {
                "None" -> {
                    CaloriesFinal -= 0
                    ActivtyLevel = "Base Rate"

                    proteinVal = 0.30F
                    CarbsValue = 0.40f
                    FatValue = 0.3f
                }
                "1-3 (Days)" -> {
                    CaloriesFinal += 400
                    ActivtyLevel = "Light"

                    proteinVal = 0.25F
                    CarbsValue = 0.40f
                    FatValue = 0.35f
                }

                "4-5 (Days)" -> {
                    CaloriesFinal += 600
                    ActivtyLevel = "Moderate"

                    proteinVal = 0.27F
                    CarbsValue = 0.43f
                    FatValue = 0.3f
                }

                "7 (Days)" -> {
                    CaloriesFinal += 1400
                    ActivtyLevel = "Very Active"

                    proteinVal = 0.25F
                    CarbsValue = 0.45f
                    FatValue = 0.3f
                }
            }
        }
    }

    when (WeightGoal) {
        "Lose" -> {
            when (ChangeIntensity) {
                "Mild" -> {
                    CaloriesFinal -= 300
                    WeightlossPerWeek = 0.25f
                }
                "Medium" -> {
                    CaloriesFinal -= 500
                    WeightlossPerWeek = 0.5f
                }
                "Faster" -> {
                    CaloriesFinal -= 600
                    WeightlossPerWeek = 0.62f
                }
            }

        }

        "Maintain" -> {
            when (ChangeIntensity) {
                "Mild" -> {
                    CaloriesFinal -= 0
                }
                "Medium" -> {
                    CaloriesFinal -= 0
                }
                "Faster" -> {
                    CaloriesFinal -= 0
                }
            }
        }

        "Gain" -> {
            when (ChangeIntensity) {
                "Mild" -> {
                    CaloriesFinal += 300
                    WeightlossPerWeek = 0.25f
                }
                "Medium" -> {
                    CaloriesFinal += 500
                    WeightlossPerWeek = 0.5f
                }
                "Faster" -> {
                    CaloriesFinal += 600
                    WeightlossPerWeek = 0.62f
                }
            }

        }
    }


    val proteinNum: Int = Math.toIntExact(Math.round(CaloriesFinal * proteinVal) / 4)
    val CarbsNum: Int = Math.toIntExact(((CaloriesFinal * CarbsValue) / 4).toLong())
    val FatNum: Int = Math.toIntExact(((CaloriesFinal * FatValue) / 9).toLong())
    val MealsNum: Int = Math.toIntExact(Math.round(proteinNum.toDouble()) / 35)

    val UserMacros: MutableMap<String, String> = HashMap()

    UserMacros["Calories"] = CaloriesFinal.toInt().toString()
    UserMacros["Protein"] = proteinNum.toString()
    UserMacros["Carbs"] = CarbsNum.toString()
    UserMacros["Fat"] = FatNum.toString()
    UserMacros["MealNum"] = MealsNum.toString()
    UserMacros["WeightLossPerWeek"] = (WeightlossPerWeek * 100).toInt().toString()
    UserMacros["Activity Level"] = ActivtyLevel

    return UserMacros


}



