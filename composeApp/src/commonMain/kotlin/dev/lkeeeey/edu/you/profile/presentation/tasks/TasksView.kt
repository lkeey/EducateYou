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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.you.auth.presentation.components.FilledBtn
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.profile.presentation.students.components.BackBtn
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksEvent
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksState

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
                        .clip(shape = RoundedCornerShape(40.dp))
                        .background(White)

                ) {
                    OutlinedText(
                        previousData = createBlockTaskModel.content,
                        label = "Содержание задачи - ${index+1}",
                    ) {
                        onEvent(TasksEvent.OnUpdateTaskContent(index = index, content = it))
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedText(
                        previousData = createBlockTaskModel.answer,
                        label = "Правильный ответ для задачи - ${index+1}",
                    ) {
                        onEvent(TasksEvent.OnUpdateTaskAnswer(index = index, answer = it))
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }

            Spacer(Modifier.height(8.dp))

            FilledBtn(
                text = "Добавить задачу",
            ) {
                onEvent(TasksEvent.OnAddTask)
            }

            Spacer(Modifier.height(24.dp))

            FilledBtn(
                text = "Сохранить",
            ) {
                onEvent(TasksEvent.OnSave)
            }
        }
    }
}
