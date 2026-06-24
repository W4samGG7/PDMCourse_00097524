package com.example.pdm_00097524.RankeUca.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pdm_00097524.RankeUca.model.Option

@Composable
fun OptionCard(option: Option, onClick:() -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp).clickable{onClick()},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = option.imageUrl,
                contentDescription = "Imagen de option"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = option.value,
                    fontWeight = FontWeight.Bold)
                Text(text = "Toca para votar"
                )
            }
        }
    }
}