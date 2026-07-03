package com.example.pdm_00097524.RankeUca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.pdm_00097524.RankeUca.ui.screens.home.HomeScreen
import com.example.pdm_00097524.RankeUca.ui.screens.options.OptionsScreen
import com.example.pdm_00097524.RankeUca.ui.screens.questions.QuestionScreen
import com.example.pdm_00097524.RankeUca.ui.screens.votes.VoteScreen


@Composable
fun RankeUCA_App(
  userName: String?,
  onLogout: () -> Unit
) {
  val backStack = rememberNavBackStack(Routes.Home)
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        HomeScreen(
          NavtoQuestions = {backStack.add(Routes.Questions)},
          NavtoVotes = {backStack.add(Routes.Votes)},
          onLogout = onLogout,
          userName = userName
        )
      }
      entry<Routes.Options> { key->
        OptionsScreen(key.questionId)
      }
      entry<Routes.Questions> {
        QuestionScreen(navigateToDetail = { questionId ->
          backStack.add(Routes.Options(questionId))
        })
      }
      entry<Routes.Votes> {
        VoteScreen()
      }
    },
  )


}