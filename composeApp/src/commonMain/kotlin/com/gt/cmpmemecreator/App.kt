package com.gt.cmpmemecreator

import androidx.compose.runtime.Composable
import com.gt.cmpmemecreator.core.presentation.NavigationRoot
import com.gt.cmpmemecreator.meme_gallery.presentation.MemeGalleryScreen
import com.gt.cmpmemecreator.core.theme.MemeCreatorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MemeCreatorTheme {
        NavigationRoot()
    }
}