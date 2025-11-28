package com.gt.cmp_memecreator.meme_editor.presentation.components

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import com.gt.cmp_memecreator.meme_editor.presentation.MemeText
import com.gt.cmp_memecreator.meme_editor.presentation.TextBoxInteractionState

@Composable
fun DraggableContainer(
    children: List<MemeText>,
    textBoxInteractionState: TextBoxInteractionState,
    onChildTransformChange: (id: String, offset: Offset, rotation: Float, scale: Float) -> Unit,
    onChildClick: (String) -> Unit,
    onChildDoubleClick: (String) -> Unit,
    onChildTextChange: (id: String, text: String) -> Unit,
    onChildDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    BoxWithConstraints {
        val parentWidth = constraints.maxWidth
        val parentHeight = constraints.maxHeight

        children.forEach { child ->
            //need parameter child.id
            //to avoid resetting / jumping of the child display
            // when actually one of the child is deleted
            var childWidth by remember(child.id) {
                mutableStateOf(0)
            }

            var childHeight by remember(child.id) {
                mutableStateOf(0)
            }

            var scale by rememberSaveable(child.id) {
                mutableStateOf(0)
            }

            val transformableState =
                rememberTransformableState { scaleChange, panChange, rotationChange ->
                    val newRotation = child.rotation + rotationChange
                    val newScale = (child.scale * scaleChange).coerceIn(0.5f, 2f)
                    val newOffset = Offset(
                        x = (child.offsetRatioX * parentWidth + panChange.x),
                        y = (child.offsetRatioY * parentHeight + panChange.y)
                    )

                    onChildTransformChange(
                        child.id,
                        newOffset,
                        newRotation,
                        newScale
                    )
                }

            Box(
                modifier = Modifier
                    .onSizeChanged {
                        childWidth = it.width
                        childHeight = it.height
                    }
                    .graphicsLayer {
                        translationX = child.offsetRatioX * parentWidth
                        translationY = child.offsetRatioY * parentHeight
                        rotationZ = child.rotation
                        scaleX = child.scale
                        scaleY = child.scale
                    }
            ) {
                MemeTextBox(
                    memeText = child,
                    textBoxInteractionState = textBoxInteractionState,
                    maxWidth = with(density) {
                        parentWidth.toDp()
                    },
                    maxHeigh = with(density) {
                        parentHeight.toDp()
                    },
                    onClick = {
                        onChildClick(child.id)
                    },
                    onDoubleClick = {
                        onChildDoubleClick(child.id)
                    },
                    onTextChange = {
                        onChildTextChange(child.id, it)
                    },
                    onDeleteClick = {
                        onChildDeleteClick(child.id)
                    }
                )
            }
        }
    }
}