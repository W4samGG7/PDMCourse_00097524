package com.example.pdm_00097524.RankeUca.ui.screens.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdm_00097524.RankeUca.ui.components.QuestionBottomSheet
import com.example.pdm_00097524.RankeUca.ui.components.QuestionCard
import com.example.pdm_00097524.RankeUca.data.model.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    navigateToDetail:(Int) -> Unit,
    viewModel: QuestionViewModel = viewModel(factory = QuestionViewModel.Factory)
){
    val questions by viewModel.questions.collectAsStateWithLifecycle()
    var showSheet by rememberSaveable { mutableStateOf(false) }
    var editQuestion by rememberSaveable {mutableStateOf<Question?>(null) }
    val error by viewModel.error.collectAsState()
    val refresh by viewModel.refreshing.collectAsState()

    if (error != null){
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Administrar opciones") },
                )
            }
        ) { paddingValues ->
            Column(modifier =
                Modifier.padding(paddingValues).padding(16.dp).background(Color.White),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${error}")
                Button(onClick = {
                    viewModel.refreshQuestions()
                }) {
                    Text(
                        text = "Reintentar",
                        color = Color.Cyan
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        topBar = {
            TopAppBar(
                title = { Text("Preguntas") },
                actions = {
                    TextButton(onClick = {
                        editQuestion = null
                        showSheet = true}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Nueva pregunta")
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
        PullToRefreshBox(
            isRefreshing = refresh,
            onRefresh = {viewModel.refreshQuestions()},
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                if (questions.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
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
                            text = "Todavia no hay preguntas",
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
                        items(items = questions, key = { it.id }) { question ->
                            QuestionCard(
                                question, onDelete = { viewModel.deleteQuestion(question) },
                                onClick = { navigateToDetail(question.id) },
                                onEdit = {
                                    editQuestion = question
                                    showSheet = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    if(showSheet){
        QuestionBottomSheet(
            question = editQuestion,
            onSave = {title ->
                if(editQuestion!=null){
                    viewModel.updateQuestion(editQuestion!!.copy(title=title))
                }
                else {
                    viewModel.addQuestion(title)
                }
                showSheet= false
                editQuestion = null
                     },
            onDismiss = {
                showSheet = false
                editQuestion=null}
        )
    }
}