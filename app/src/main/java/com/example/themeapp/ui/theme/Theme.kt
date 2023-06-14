package com.example.themeapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.themeapp.ui.components.data.CustomTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LocalColors = staticCompositionLocalOf { ThemeACustomColors }

val MaterialTheme.customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

@Composable
fun AppTheme(
    theme: CustomTheme = CustomTheme.A,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        CustomTheme.B -> ThemeBCustomColors
        CustomTheme.C -> ThemeCCustomColors
        CustomTheme.D -> ThemeDCustomColors
        else -> ThemeACustomColors
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

    val useDarkIcons = when (theme) {
        CustomTheme.C, CustomTheme.D -> false
        else -> true
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(colors.statusBarColor, useDarkIcons)
}