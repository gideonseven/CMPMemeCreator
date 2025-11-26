package com.gt.cmp_memecreator.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.meme_template_01
import com.gt.cmp_memecreator.core.presentation.MemeTemplate
import com.gt.cmp_memecreator.core.theme.MemeCreatorTheme
import com.gt.cmp_memecreator.meme_editor.presentation.components.MemeTextBox
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MemeEditorRoot(
    template: MemeTemplate,
    onGoBack: () -> Unit,
    viewModel: MemeEditorViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MemeEditorScreen(
        template = template,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MemeEditorScreen(
    template: MemeTemplate,
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(template.drawable),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        state.memeTexts.forEach { memeText ->
            MemeTextBox(
                memeText = memeText,
                textBoxInteractionState = state.textBoxInteractionState,
                maxWidth = 500.dp,
                maxHeigh = 500.dp,
                onClick = {
                    onAction(MemeEditorAction.OnSelectMemeText(memeText.id))
                },
                onDoubleClick = {
                    onAction(MemeEditorAction.OnEditMemeText(memeText.id))
                },
                onTextChange = {
                    onAction(MemeEditorAction.OnMemeTextChange(memeText.id, it))
                },
                onDeleteClick = {
                    onAction(MemeEditorAction.OnDeleteMemeTextClick(memeText.id))
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MemeCreatorTheme {
        MemeEditorScreen(
            template = MemeTemplate(
                id = "meme_template_01",
                drawable = Res.drawable.meme_template_01
            ),
            state = MemeEditorState(),
            onAction = {}
        )
    }
}