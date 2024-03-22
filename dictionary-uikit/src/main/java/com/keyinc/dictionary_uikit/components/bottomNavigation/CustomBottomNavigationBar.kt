package com.keyinc.dictionary_uikit.components.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium


@Composable
@Preview(showBackground = true)
fun CustomBottomNavigationBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    InkGray,
                    RoundedCornerShape(PaddingMedium, PaddingMedium, 0.dp, 0.dp)
                )
                .clip(RoundedCornerShape(PaddingMedium, PaddingMedium, 0.dp, 0.dp)),
            containerColor = Color.White,
        ) {

        }
    }

}