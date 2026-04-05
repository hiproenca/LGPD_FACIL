package com.hig.lgpd_facil.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LgpdColorScheme = lightColorScheme(
    primary = AzulCorporativo,
    secondary = VerdeConformidade,
    tertiary = LaranjaAlerta,
    background = FundoClaro,
    surface = SuperficieBranca,
    onPrimary = Color.White,
    onBackground = TextoPrincipal,
    onSurface = TextoPrincipal
)

@Composable
fun Lgpd_FacilTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = AzulCorporativo.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = LgpdColorScheme,
        typography = Typography,
        content = content
    )
}