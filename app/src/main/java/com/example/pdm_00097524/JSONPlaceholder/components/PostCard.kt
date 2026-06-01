package com.example.pdm_00097524.JSONPlaceholder.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pdm_00097524.JSONPlaceholder.model.Post

@Composable
fun PostCard(post: Post){
    Card(
        modifier = Modifier.fillMaxWidth().height(440.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = "Numero post: ${post.id}")
            Text(text = "Usuario: ${post.userId}")
            Text(text = "titulo: ${post.title}",
                overflow = TextOverflow.Ellipsis)
            Text(text = "Contenido: ${post.body}",
                overflow = TextOverflow.Ellipsis,
                )
        }
    }
}