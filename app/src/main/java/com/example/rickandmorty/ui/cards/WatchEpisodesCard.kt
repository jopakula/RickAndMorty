package com.example.rickandmorty.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WatchEpisodesCard(
) {
    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFFF6B00).copy(alpha = 0.1F),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color(0xFFFF6B00)
            )
            Text(
                text = "Watch episodes",
                color = Color(0xFFFF6B00),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        }

    }
}
