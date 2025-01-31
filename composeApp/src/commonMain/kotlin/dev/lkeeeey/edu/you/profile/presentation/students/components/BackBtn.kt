package dev.lkeeeey.edu.you.profile.presentation.students.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.core.presentation.Theme
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackBtn(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    containerColor: Color = Theme.colors.backgroundMain,
) {

    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(horizontal = 12.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Theme.colors.blackProfile,
            actionIconContentColor = Theme.colors.blackProfile,
            containerColor = containerColor
        ),
        title = {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = Theme.colors.blackProfile,
                    textAlign = TextAlign.Center
                )
            )
        },
        navigationIcon = {
            //Back button
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                modifier = modifier.clickable {
                    onClick()
                }
            )
//            Icon(
//                modifier = Modifier
//                    .size(24.dp)
//                    .clip(CircleShape)
//                    .clickable {
//                        onClick()
//                    },
//                painter = painterResource(Res.drawable.ic_btn_back),
//                contentDescription = "back",
//            )
        },
        actions = {
            //Search button
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = null,
//                modifier = modifier
//                    .clickable {
//                        /* Handle search icon click */
//                    }
//                    .scale(1.2f)
//            )
        }
    )
}
