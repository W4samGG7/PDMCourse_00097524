package com.example.pdm_00097524.RankeUca.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pdm_00097524.RankeUca.data.model.Option

@Composable
fun OptionCard(option: Option, onDelete:() -> Unit ,onEdit:() -> Unit){
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
    ){
        ListItem(
            headlineContent = {
                Text(
                    text = option.value,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Text(
                    text = option.imageUrl ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                Row() {
                    IconButton(onClick = { onEdit() }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar ${option.value}",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    IconButton(onClick = { onDelete() }) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Borrar ${option.value}",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        )
    }
}