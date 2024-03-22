package com.yuriyyangel.wordsfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.components.bottomNavigation.BottomBar
import com.yuriyyangel.wordsfactory.ui.theme.WordsFactoryTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                contentWindowInsets = WindowInsets(0.dp),
                modifier = Modifier.fillMaxSize(),
                topBar = {},
                content = {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .safeDrawingPadding()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Greeting("Android")
                    }
                },
                bottomBar = {
                    BottomBar()
                }
            )

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordsFactoryTheme {
        Greeting("Android")
    }
}