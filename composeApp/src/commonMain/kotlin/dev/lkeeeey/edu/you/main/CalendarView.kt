package dev.lkeeeey.edu.you.main

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.main.presentation.calendar.components.MonthViewCalendar
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.main.components.ImageWithText
import dev.lkeeeey.edu.you.main.components.MonthText
import dev.lkeeeey.edu.you.main.viewmodel.CalendarAction
import dev.lkeeeey.edu.you.main.viewmodel.CalendarEvent
import dev.lkeeeey.edu.you.main.viewmodel.CalendarState
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.ic_calendar_no_plans
import educateyou.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun CalendarView (
    state: CalendarState,
    onEvent: (CalendarEvent) -> Unit,
    onOpen: (CalendarAction) -> Unit
) {

    val loadedDates = state.loadedDates
    val selectedDate = state.selectedDate
    val currentMonth = state.currentDate.month
    val scroll = rememberScrollState()

    Scaffold(
        backgroundColor = Theme.colors.backgroundMain,
    ) { innerPadding->
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .animateContentSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .systemBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        color = White,
                        shape = RoundedCornerShape(
                            size = 8.dp
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MonthText(
                        selectedMonth = currentMonth
                    )

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(50.dp)
                            .width(50.dp)
                            .clickable {
                                onOpen(CalendarAction.OnOpenProfile)
                            },
                        painter = painterResource(Res.drawable.profile),
                        contentDescription = "profile img",
                        contentScale = ContentScale.FillWidth
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                MonthViewCalendar(
                    loadedDates,
                    selectedDate,
                    onDayClick = {
                        onEvent(CalendarEvent.OnDayClick(it))
                    }
                )
            }

            Column (
                modifier = Modifier
                    .verticalScroll(scroll)
            ) {
                Spacer(Modifier.height(32.dp))

                if (state.lessons.isEmpty()) {
                    ImageWithText (
                        drawable = Res.drawable.ic_calendar_no_plans,
                        text = "Здесь пусто. Можно и отдохнуть"
                    )
                } else {
                    state.lessons.forEach {
                        ScheduledLesson()
                    }
                }


                Spacer(Modifier.height(32.dp))
            }
        }
    }
}
