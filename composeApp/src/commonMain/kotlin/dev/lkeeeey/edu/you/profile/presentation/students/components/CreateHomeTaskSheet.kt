package dev.lkeeeey.edu.you.profile.presentation.students.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsEvent
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsState
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerComponent.WheelDatePicker
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults
import network.chaintech.kmp_date_time_picker.utils.now
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateHomeTaskSheet (
    state: StudentsState,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onEvent: (StudentsEvent) -> Unit,
    onDismiss: () -> Unit
) {
    if (isBottomSheetVisible) {

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            shape = RectangleShape,
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
        ) {

            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                FilledIconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        onEvent(StudentsEvent.OnSave)

                        onDismiss()
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Theme.colors.backgroundMain
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Done,
                        contentDescription = "Hide the dialog."
                    )
                }
            }

            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(12.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(color = Theme.colors.backgroundMain)
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedText(
                    previousData = state.enteredContent,
                    label = "Описание",
                ) {
                    onEvent(StudentsEvent.OnEnterContent(it))
                }

                Spacer(modifier = Modifier.height(8.dp))

                ReadOnlyDropDown(
                    options = state.loadedSubjectsPres,
                    previousData = state.enteredSubject.name,
                    label = "Дисциплина"
                ) { subj ->
                    onEvent(
                        StudentsEvent.OnEnterSubject(
                            subject = subj
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                NumTextField(
                    previousData = state.enteredTime.toString(),
                    label = "Время выполнения",
                ) {
                    onEvent(StudentsEvent.OnEnterExecutionTime(it))
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Дедлайн",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(500),
                        color = Theme.colors.editPlaceholder,
                        letterSpacing = 0.3.sp
                    ),
                    textAlign = TextAlign.Center
                )

                WheelDatePicker(
                    startDate = LocalDate.now().plus(DatePeriod(days = 1)),
                    minDate = LocalDate.now().plus(DatePeriod(days = 1)),
                    dateTextStyle = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(500),
                        color = Theme.colors.editPlaceholder,
                        letterSpacing = 0.3.sp
                    ),
                    dateTextColor = Theme.colors.primaryTextColor.copy(1f),
                    hideHeader = true,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        borderColor = Theme.colors.primaryBackground.copy(1f)
                    ),
                    onDateChangeListener = {
                        onEvent(StudentsEvent.OnEnterDeadline(it))
                    }
                )
            }
        }
    }
}
