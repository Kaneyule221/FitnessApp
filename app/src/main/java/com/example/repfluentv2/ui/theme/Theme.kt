package com.example.repfluentv2.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Purple700,
    secondary = Teal200,

    background = Color(0xFFF5C085),
    surface = Color(0xFFBFEFFB),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

)

data class AppResources(
    @DrawableRes val someDrawable: Int,
    @StringRes val someString: Int,
)

val LocalAppResources = staticCompositionLocalOf<AppResources> {
    error("CompositionLocal LocalAppResources not present")
}

val GradientColor =       Brush.linearGradient(
    colors = listOf(Color(0xffbfeffb), Color(0xfff1fafd)),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)

val GradientColor2 =       Brush.linearGradient(
    colors = listOf(Color(0xfffba94f), Color(0xffffe1c3)),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)

@Composable
fun RepfluentV2Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUiController.setSystemBarsColor(
        color = Color(0xffbfeffb)
    )
        DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(
            color = Color(0xFFF2F4F5)
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = com.example.repfluentv2.ui.theme.ui.theme.aarghTypography,
        shapes = Shapes,
        content = content
    )
}