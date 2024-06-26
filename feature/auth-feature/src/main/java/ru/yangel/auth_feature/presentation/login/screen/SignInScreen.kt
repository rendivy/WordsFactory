package ru.yangel.auth_feature.presentation.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.snackbar.SnackBar
import com.keyinc.dictionary_uikit.components.textfield.AccentTextField
import com.keyinc.dictionary_uikit.components.textfield.PasswordTextField
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import ru.yangel.auth_feature.R
import ru.yangel.auth_feature.presentation.login.SignInViewModel
import ru.yangel.auth_feature.presentation.login.state.LoginState

@Composable
fun SignInScreen(navigateToHome: () -> Unit = {}) {
    SignInScreen(viewModel = hiltViewModel(), navigateToHome = navigateToHome)
}

@Composable
internal fun SignInScreen(viewModel: SignInViewModel, navigateToHome: () -> Unit = {}) {

    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackBarHostState = remember { SnackbarHostState() }
    var snackBarLauncher by remember { mutableStateOf(false) }
    val snackBarMessage = stringResource(id = R.string.snackbar_error)

    if (snackBarLauncher) {
        LaunchedEffect(snackBarMessage) {
            snackBarHostState.showSnackbar(snackBarMessage)
        }
    }

    when (loginState) {
        is LoginState.Loading -> snackBarLauncher = false
        is LoginState.Success -> navigateToHome()
        is LoginState.Error -> snackBarLauncher = true
        is LoginState.Initial -> snackBarLauncher = false

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { SnackBar(message = it.visuals.message) }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .padding(PaddingMedium)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_login),
                modifier = Modifier
                    .padding(PaddingMedium)
                    .aspectRatio(1.2f),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.sign_in_button),
                style = Heading1
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            Text(
                text = stringResource(id = R.string.login_account),
                style = ParagraphMedium
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentTextField(
                placeHolderValue = stringResource(id = R.string.email),
                textFieldValue = loginUiState.email,
                onValueChange = viewModel::onEmailChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            PasswordTextField(
                placeHolderValue = stringResource(id = R.string.password),
                textFieldValue = loginUiState.password,
                onValueChange = viewModel::onPasswordChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentButton(
                text = stringResource(id = R.string.sign_in_button),
                onClick = { viewModel.loginUser() },
            )
        }

    }
}