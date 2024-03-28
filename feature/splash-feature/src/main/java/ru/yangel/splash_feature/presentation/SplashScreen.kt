package ru.yangel.splash_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.dictionary_uikit.theme.Display2
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import kotlinx.coroutines.delay
import ru.yangel.splash_feature.R
import ru.yangel.splash_feature.SplashViewModel
import ru.yangel.splash_feature.presentation.state.SplashState

@Composable
fun SplashScreen(
    onNavigateToRegistration: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    SplashScreen(
        viewModel = hiltViewModel(),
        onNavigateToRegistration = onNavigateToRegistration,
        onNavigateToLogin = onNavigateToLogin
    )
}

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToRegistration: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
) {
    val splashState by viewModel.splashState.collectAsStateWithLifecycle()

    when (splashState) {
        is SplashState.Initial -> viewModel.checkUserLogin()
        is SplashState.NotLogin -> onNavigateToRegistration()
        is SplashState.AlreadyLogin -> onNavigateToLogin()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_ic),
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.splash_label),
            modifier = Modifier.padding(PaddingMedium),
            style = Display2,
        )
    }
}