package dev.lkeeeey.edu.you.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.you.auth.presentation.components.OutlinedText
import dev.lkeeeey.edu.you.auth.presentation.components.PasswordField
import dev.lkeeeey.edu.you.auth.presentation.components.FilledBtn
import dev.lkeeeey.edu.you.auth.presentation.components.FooterAuth
import dev.lkeeeey.edu.you.auth.presentation.components.Header
import dev.lkeeeey.edu.you.auth.presentation.login.viewmodel.LoginAction
import dev.lkeeeey.edu.you.auth.presentation.login.viewmodel.LoginState
import dev.lkeeeey.edu.you.core.presentation.Theme
import dev.lkeeeey.edu.you.profile.presentation.components.ErrorMessage
import educateyou.composeapp.generated.resources.Res
import educateyou.composeapp.generated.resources.Thin
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font


@Composable
fun LoginView(
    scaffoldState: ScaffoldState,
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(Theme.colors.secondaryBackground)
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                "Войти в сервис"
            )

            if (state.isError) {
                ErrorMessage(state.errorMessage)
            }

            OutlinedText(
                previousData = state.username,
                label = "Телеграмм",
            ) {
                onAction(LoginAction.OnUsernameChanged(it))
            }

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = state.password,
                label = "Пароль",
                isError = state.isError,
                errorText = state.errorMessage
            ) {
                onAction(LoginAction.OnPasswordChanged(it))
            }

            Spacer(modifier = Modifier.height(24.dp))

            FilledBtn(
                padding = 0.dp,
                isEnabled = state.isButtonEnabled,
                text = "Войти",
            ) {
                onAction(LoginAction.OnLogin)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Sorry, it is in development...",
                            )
                        }
                    },
                text = "Забыл пароль",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Theme.colors.secondaryScreen)
                .padding(horizontal = 54.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterAuth(
                content = "Еще не регистрировались в сервисах EducateMe?",
                offer = "Зарегистрировать учетную запись"
            ) {
                onAction(LoginAction.OnSignUp)
            }
        }
    }

}

//private fun Modifier.isLoading(isLoading: Boolean) = Modifier
//    .blur(if (isLoading) 4.dp else 0.dp)



