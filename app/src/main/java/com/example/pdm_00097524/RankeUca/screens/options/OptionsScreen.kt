package com.example.pdm_00097524.RankeUca.screens.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import com.example.pdm_00097524.RankeUca.components.OptionBottomSheet
import com.example.pdm_00097524.RankeUca.components.OptionCard
import com.example.pdm_00097524.RankeUca.model.Option


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(
    questionId: Int,
    viewModel: OptionsViewModel = viewModel(
        key = "options_$questionId",
        factory = OptionsViewModel.provideFactory(questionId)
    )
){
    val options by viewModel.options.collectAsStateWithLifecycle()
    var showSheet by rememberSaveable { mutableStateOf(false) }
    var editOption by rememberSaveable {mutableStateOf<Option?>(null) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        topBar = {
            TopAppBar(
                title = { Text("Administrar opciones") },
                actions = {
                    TextButton(onClick = {
                        editOption = null
                        showSheet = true}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Nueva opcion")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Nuevo")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (options.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Inbox,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.height(36.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Todavia no hay opciones",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Toca Nuevo para crear la primera",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = options, key = {it.id}){ option ->
                        OptionCard(option,
                            onDelete = {viewModel.deleteOption(option)},
                            onEdit = {
                                editOption = option
                                showSheet = true
                            })
                    }
                }
            }
        }
    }
    if(showSheet){
        OptionBottomSheet(
            option = editOption,
            onSave = {value, imageUrl ->
                if(editOption != null){
                    viewModel.updateOption(editOption!!.copy(value = value, imageUrl = imageUrl))
                }
                else {
                    viewModel.addOption(value, imageUrl)
                }
            },
            onDismiss = {
                showSheet = false
                editOption = null
            }
        )
    }
}