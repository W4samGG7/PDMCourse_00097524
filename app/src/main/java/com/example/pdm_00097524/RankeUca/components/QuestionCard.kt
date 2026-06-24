package com.example.pdm_00097524.RankeUca.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pdm_00097524.RankeUca.model.Question

@Composable
fun QuestionCard(
    question: Question,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = question.title,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Text(
                    text = "Cantidad de opciones: ${question.optionCount}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Borrar ${question.title}",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
    }
}