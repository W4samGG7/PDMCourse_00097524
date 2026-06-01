package com.example.pdm_00097524.JSONPlaceholder.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pdm_00097524.JSONPlaceholder.model.Post

@Composable
fun PostDetailCard (post: Post){
    Card(
        modifier = Modifier.fillMaxWidth().height(440.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(4),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            /*
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = "https://images.unsplash.com/photo-1611915365928-565c527a0590?q=80&w=1025",
                contentDescription = "placeHolder"
            )

             */
            Text(text = "Numero de post: ${post.id}")
            Text(text = "Usuario: ${post.userId}")
            Text(text = "Titulo: ${post.title}")
            Text(text = "Contenido: ${post.body}",
                overflow = TextOverflow.Ellipsis)
        }
    }
}