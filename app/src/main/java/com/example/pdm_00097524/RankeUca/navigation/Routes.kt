package com.example.pdm_00097524.RankeUca.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {

  @Serializable
  data object Home: Routes()

  @Serializable
  data object Votes: Routes()

  @Serializable
  data class Options(val questionId: Int): Routes()

  @Serializable
  data object Questions: Routes()
}