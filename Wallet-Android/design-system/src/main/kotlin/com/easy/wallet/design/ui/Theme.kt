package com.easy.wallet.design.ui

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.easy.wallet.design.theme.BackgroundTheme
import com.easy.wallet.design.theme.GradientColors
import com.easy.wallet.design.theme.LocalBackgroundTheme
import com.easy.wallet.design.theme.LocalGradientColors

val InkBlack = Color(0xFF000000)
val InkGray = Color(0xFF333333)
val InkWhite = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = InkBlack,
    onPrimary = InkWhite,
    secondary = InkWhite,
    onSecondary = InkBlack,
    background = InkWhite,
    onBackground = InkBlack,
    surface = InkWhite,
    onSurface = InkBlack
)

private val DarkColorScheme = darkColorScheme(
    primary = InkBlack,
    onPrimary = InkWhite,
    secondary = InkWhite,
    onSecondary = InkBlack,
    background = InkBlack,
    onBackground = InkWhite,
    surface = InkBlack,
    onSurface = InkWhite
)

val LightAndroidGradientColors = GradientColors(container = Color.White)

/**
 * Dark Android gradient colors
 */
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = Color.White)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

@Composable
fun EWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val gradientColors = when {
        darkTheme -> DarkAndroidGradientColors
        else -> LightAndroidGradientColors
    }

    val backgroundTheme = when {
        darkTheme -> DarkAndroidBackgroundTheme
        else -> LightAndroidBackgroundTheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = backgroundTheme.color.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
