package navigation

import Screens.Display
import Screens.MainMenu
import Screens.MenuCard
import android.os.Build
import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.repfluentv2.ui.theme.RepfluentV2Theme

@Composable
fun MenuMain(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Text(text = "Hello $name!")
    }
}


@Composable
@Preview
fun MenuMainPreview(Name: String = "i") {
    Display(Name)
}