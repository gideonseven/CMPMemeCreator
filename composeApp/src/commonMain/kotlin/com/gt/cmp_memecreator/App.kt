package com.gt.cmp_memecreator

import androidx.compose.runtime.Composable
import com.gt.cmp_memecreator.core.presentation.NavigationRoot
import com.gt.cmp_memecreator.core.theme.MemeCreatorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MemeCreatorTheme {
        NavigationRoot()
    }
}