package conceptosBasicosDeCompose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Row(Modifier.weight(1f)) {
            InfoBox(
                title = stringResource(title_text),
                explanation = stringResource(explanation_text),
                backgroundColor = colorResource(R.color.purple_100),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                title = stringResource(title_image),
                explanation = stringResource(explanation_image),
                backgroundColor = colorResource(R.color.purple_200),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            InfoBox(
                title = stringResource(title_row),
                explanation = stringResource(explanation_row),
                backgroundColor = colorResource(R.color.purple_400),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                title = stringResource(title_column),
                explanation = stringResource(explanation_column),
                backgroundColor = colorResource(R.color.pink_100),
                modifier = Modifier.weight(1f)
            )
        }
    }
    }
}


@Composable
private fun InfoBox(
    title: String,
    explanation: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = explanation,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun quadrantPreview() {
    quadrant()
}

