package dev.lkeeeey.edu.you.profile.presentation.students

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.components.ErrorMessage
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsAction
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsEvent
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsState
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun StudentsView (
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit,
    onOpen: (StudentsAction) -> Unit
) {
    Column (
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.errorMessage.isNotEmpty()) {
            ErrorMessage(state.errorMessage)
        }

        Text(
            text = "Мои ученики",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(600),
                color = Theme.colors.blackProfile,
                textAlign = TextAlign.Center
            )
        )

        Spacer(Modifier.height(16.dp))

        if (state.students.isNotEmpty()) {
            state.students.forEachIndexed { index, student ->

            }
        }
    }
}
