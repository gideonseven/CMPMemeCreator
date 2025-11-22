package com.gt.cmpmemecreator.core.presentation

import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.allDrawableResources
import org.jetbrains.compose.resources.DrawableResource


data class MemeTemplate(
    val id: String,
    val drawable: DrawableResource
)

val memeTemplate = Res
    .allDrawableResources
    .filterKeys { it.startsWith(prefix = "meme_template") }
    .map { (key, value) ->
        MemeTemplate(
            id = key,
            drawable = value
        )
    }