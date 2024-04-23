package ru.yangel.video_feature

import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.keyinc.dictionary_uikit.theme.PrimaryColor

@Composable
fun VideoScreen() {
    val mUrl = "https://learnenglish.britishcouncil.org/general-english/video-zone"
    var showLoader by rememberSaveable { mutableStateOf(false) }

    if (showLoader) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = PrimaryColor
            )
        }
    }

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            visibility = android.view.View.VISIBLE
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val currentUrl = request?.url.toString()
                    val currentDomain = Uri.parse(currentUrl).host
                    val originalDomain = Uri.parse(mUrl).host
                    return currentDomain != originalDomain
                }


                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    view?.visibility = android.view.View.INVISIBLE
                    showLoader = true
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.visibility = android.view.View.VISIBLE
                    showLoader = false
                    super.onPageFinished(view, url)
                }
            }
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

