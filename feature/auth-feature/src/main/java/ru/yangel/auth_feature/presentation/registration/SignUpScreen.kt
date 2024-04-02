package ru.yangel.auth_feature.presentation.registration


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import ru.yangel.auth_feature.R
import ru.yangel.auth_feature.presentation.registration.state.RegistrationError
import ru.yangel.auth_feature.presentation.registration.state.RegistrationState

@Composable
fun SignUpScreen(onNavigateToLogin: () -> Unit, onNavigateToHome: () -> Unit) {
    SignUpScreen(viewModel = hiltViewModel(), onNavigateToLogin = onNavigateToLogin, navigateToHome = onNavigateToHome)
}


@Composable
internal fun SignUpScreen(viewModel: SignUpViewModel, onNavigateToLogin: () -> Unit, navigateToHome: () -> Unit) {
    val registrationUiState by viewModel.registrationUiState.collectAsStateWithLifecycle()
    val registrationState by viewModel.registrationState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackBarHostState = remember { SnackbarHostState() }
    var snackBarLauncher by remember { mutableStateOf(false) }
    var snackBarMessage = stringResource(id = R.string.snackbar_error)


    if (snackBarLauncher) {
        LaunchedEffect(snackBarMessage) {
            snackBarHostState.showSnackbar(snackBarMessage)
        }
    }

    when (registrationState) {
        is RegistrationState.Loading -> snackBarLauncher = false
        is RegistrationState.Success -> navigateToHome()
        is RegistrationState.Error -> {
            val error = (registrationState as RegistrationState.Error).registrationError
            snackBarMessage = when (error) {
                RegistrationError.NETWORK_ERROR -> "Unexpected error"
                RegistrationError.EMAIL_ALREADY_BUSY -> "Email already used"
            }
            snackBarLauncher = true
        }
        is RegistrationState.Initial -> snackBarLauncher = false

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
                .padding(PaddingMedium)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_login),
                modifier = Modifier
                    .padding(PaddingMedium)
                    .aspectRatio(1.2f),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.sign_up_button),
                style = Heading1
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            Text(
                text = stringResource(id = R.string.create_account),
                style = ParagraphMedium
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentTextField(
                placeHolderValue = stringResource(id = R.string.name),
                textFieldValue = registrationUiState.name,
                onValueChange = viewModel::onNameChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentTextField(
                placeHolderValue = stringResource(id = R.string.email),
                textFieldValue = registrationUiState.email,
                onValueChange = viewModel::onEmailChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            PasswordTextField(
                placeHolderValue = stringResource(id = R.string.password),
                textFieldValue = registrationUiState.password,
                onValueChange = viewModel::onPasswordChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentButton(
                text = stringResource(id = R.string.sign_up_button),
                onClick = { viewModel.onSignUpClick() },
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_an_account),
                    style = ParagraphMedium
                )
                Text(
                    text = stringResource(id = R.string.spannable_part),
                    modifier = Modifier.clickable { onNavigateToLogin() },
                    color = PrimaryColor,
                    style = ParagraphMedium
                )
            }

        }
    }
}