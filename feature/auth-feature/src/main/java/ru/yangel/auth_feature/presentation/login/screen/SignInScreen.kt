package ru.yangel.auth_feature.presentation.login.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.buttons.OutlinedAccentButton
import com.keyinc.dictionary_uikit.components.snackbar.SnackBar
import com.keyinc.dictionary_uikit.components.textfield.AccentTextField
import com.keyinc.dictionary_uikit.components.textfield.PasswordTextField
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import kotlinx.coroutines.launch
import ru.yangel.auth_feature.R
import ru.yangel.auth_feature.presentation.login.SignInViewModel

@Composable
fun SignInScreen() {
    SignInScreen(viewModel = hiltViewModel())
}

@Composable
internal fun SignInScreen(viewModel: SignInViewModel) {
    val signUpState by viewModel.signInState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { SnackBar(message = "Please, check correctness of your credentials") }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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
                textFieldValue = signUpState.email,
                onValueChange = viewModel::onEmailChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            PasswordTextField(
                placeHolderValue = stringResource(id = R.string.password),
                textFieldValue = signUpState.password,
                onValueChange = viewModel::onPasswordChange
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentButton(
                text = stringResource(id = R.string.sign_in_button),
                onClick = {
                    viewModel.loginUser()
                    scope.launch {
                        if (signUpState.isEmailValid == false) {
                            snackbarHostState.showSnackbar("Invalid email")
                        }

                    }
                },
            )
            Text(
                text = stringResource(id = R.string.or),
                modifier = Modifier.padding(PaddingMedium),
                style = ParagraphMedium
            )
            OutlinedAccentButton(text = "Continue with Google")
            OutlinedAccentButton(
                modifier = Modifier.padding(top = 16.dp),
                text = "Continue with Facebook"
            )
        }

    }
}