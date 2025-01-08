package dev.lkeeeey.edu.you.profile.presentation.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.components.ErrorMessage
import dev.lkeeeey.edu.you.profile.presentation.components.Reference
import dev.lkeeeey.edu.you.profile.presentation.students.components.BackBtn
import dev.lkeeeey.edu.you.profile.presentation.tabs.components.BottomSheet
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileAction
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileEvent
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileState
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import educateyou.composeapp.generated.resources.ic_profile_logout
import educateyou.composeapp.generated.resources.ic_profile_timetable
import educateyou.composeapp.generated.resources.ic_profile_win
import educateyou.composeapp.generated.resources.profile
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
    onOpenScreen: (ProfileAction) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White, shape = RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomEnd = 40.dp, bottomStart = 40.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BackBtn(
                text = "Profile",
                onClick = {
                    onOpenScreen(ProfileAction.OnBackScreen)
                },
                containerColor = White
            )

            if (state.errorMessage.isNotEmpty()) {
                ErrorMessage(state.errorMessage)
            }

            Image(
                modifier = Modifier
                    .size(95.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape),
                painter = painterResource(Res.drawable.profile),
                contentDescription = "logo",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column (
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        scope.launch {
                            isBottomSheetVisible = true
                            sheetState.expand()
                        }
                    }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = state.profile.name, style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(600),
                        color = Theme.colors.blackProfile,
                        letterSpacing = 0.4.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(
                    Modifier.height(4.dp)
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Учитель", style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                        fontWeight = FontWeight(600),
                        color = Theme.colors.greyDescription,
                        letterSpacing = 0.4.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(
                    Modifier.height(12.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .background(
                    color = White,
                    shape = RoundedCornerShape(size = 25.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Reference(
                icon = Res.drawable.ic_profile_win,
                title = "Ученики",
                content = "Те, кому я даю знания",
                isVisible = true
            ) {
                onOpenScreen(ProfileAction.OnOpenStudents)
            }

            Reference(
                icon = Res.drawable.ic_profile_timetable,
                title = "Расписание",
                content = "Уроки с учениками",
                isVisible = true
            ) {
//                onOpenScreen(ProfileAction.OnOpenTimeTable)
            }


            Reference(
                icon = Res.drawable.ic_profile_logout,
                title = "Выйти из аккаунта",
                content = "Уверены?",
                isLogout = true,
            ) {
                onEvent(ProfileEvent.OnLogOut)

                onOpenScreen(ProfileAction.OnLogOut)
            }
        }
    }

    BottomSheet(
        state = state,
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onEvent = onEvent,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { isBottomSheetVisible = false }
        }
    )
}
