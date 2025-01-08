package dev.lkeeeey.edu.you.profile.presentation.tabs.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileEvent
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet (
    state: ProfileState,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onEvent: (ProfileEvent) -> Unit,
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
                    onClick = onDismiss,
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
                    .padding(12.dp) // Outer padding
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(color = Theme.colors.backgroundMain)
                    .fillMaxWidth()
                    .padding(24.dp) // Inner padding
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedText(
                    previousData = state.profile.bio,
                    label = "Bio",
                ) {
                    onEvent(ProfileEvent.OnBioUpdated(it))
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedText(
                    previousData = state.profile.profileLogoUrl,
                    label = "Предмет",
                ) {
                    onEvent(ProfileEvent.OnSubjectUpdated(it))
                }

                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}
