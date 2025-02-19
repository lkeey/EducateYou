package dev.lkeeeey.edu.you.profile.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.you.auth.presentation.components.FilledBtn
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.students.components.BackBtn
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksEvent
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksState
import kotlinx.coroutines.launch

@Composable
fun TasksView (
    state: TasksState,
    onEvent: (TasksEvent) -> Unit,
    onOpenBack: () -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp)
            .verticalScroll(rememberScrollState())
    ) {

        BackBtn(
            text = "Добавление материала",
            onClick = {
                onOpenBack()
            }
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)

        ) {
            OutlinedText(
                previousData = state.enteredBlock.title,
                label = "Название",
            ) {
                onEvent(TasksEvent.OnEnteredTitle(it))
            }

            Spacer(Modifier.height(8.dp))

            OutlinedText(
                previousData = state.enteredBlock.title,
                label = "Предмет",
            ) {
                onEvent(TasksEvent.OnEnteredSubject(it))
            }

            Spacer(Modifier.height(8.dp))

            state.enteredBlock.tasks.forEachIndexed { index, createBlockTaskModel ->
                Column (
                    modifier = Modifier
                        .background(White)
                        .clip(shape = RoundedCornerShape(16.dp))
                ) {
                    OutlinedText(
                        previousData = state.enteredBlock.title,
                        label = "Содержание задачи - ${index+1}",
                    ) {
                        onEvent(TasksEvent.OnUpdateTaskContent(index = index, content = it))
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedText(
                        previousData = state.enteredBlock.title,
                        label = "Правильный ответ для задачи - ${index+1}",
                    ) {
                        onEvent(TasksEvent.OnUpdateTaskAnswer(index = index, answer = it))
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            FilledBtn(
                text = "Добавить задачу",
            ) {
                onEvent(TasksEvent.OnAddTask)
            }

            Spacer(modifier = Modifier.weight(.1f))

            // make it at the right bottom corner
            FloatingActionButton(
                onClick = {
                    onEvent(TasksEvent.OnSave)
                },
                backgroundColor = Theme.colors.primaryBackground.copy(1f)
            ) {
                Icon(
                    tint = White,
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "add"
                )
            }
        }
    }
}
