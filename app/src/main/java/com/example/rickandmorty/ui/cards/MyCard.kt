package com.example.rickandmorty.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyCard(
    text: String = "",
    color: Color = Color.Transparent,
) {

    Row(
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.1F),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = text,
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}
