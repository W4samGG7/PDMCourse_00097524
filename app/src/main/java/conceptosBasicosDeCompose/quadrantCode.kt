package conceptosBasicosDeCompose


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pdm_00097524.R

@Composable
fun quadrant(modifier: Modifier = Modifier) {
    val title_text = R.string.quadrant_text_title
    val explanation_text = R.string.quadrant_text_explanation
    val title_image = R.string.quadrant_image_title
    val explanation_image = R.string.quadrant_image_explanation
    val title_row = R.string.quadrant_row_title
    val explanation_row = R.string.quadrant_row_explanation
    val title_column = R.string.quadrant_column_title
    val explanation_column = R.string.quadrant_column_explanation

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Text(
                text = stringResource(title_text)
            )
            Text(
                text = stringResource(explanation_text)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun quadrantPreview() {
    quadrant()
}