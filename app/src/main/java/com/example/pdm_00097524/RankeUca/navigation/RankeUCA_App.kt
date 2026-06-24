package com.example.pdm_00097524.RankeUca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.pdm_00097524.RankeUca.screens.options.OptionsScreen
import com.example.pdm_00097524.RankeUca.screens.questions.QuestionScreen



@Composable
fun RankeUCA_App() {
  val backStack = rememberNavBackStack(Routes.Questions)
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Options> { key->
        OptionsScreen(key.questionId)
      }
      entry<Routes.Questions> {
        QuestionScreen(navigateToDetail = { questionId ->
          backStack.add(Routes.Options(questionId))
        })
      }
    },
  )


}