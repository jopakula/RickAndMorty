package com.example.rickandmorty.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmorty.domain.models.MyCharacter

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: MyCharacter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        GlideImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier.size(120.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = character.name, fontWeight = FontWeight.Bold)
            Text(text = "Статус: ${character.status}")
            Text(text = "Гендер: ${character.gender}")
            Text(text = "Планета: ${character.origin.name}")
        }
    }
}
