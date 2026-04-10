package conceptosBasicosDeCompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pdm_00097524.R

@Composable
fun articulo(modifier: Modifier = Modifier) {
    val image_compose = painterResource(R.drawable.bg_compose_background)
    val title = R.string.title_paragraph
    val jepack_text = R.string.jetpack_explanation
    val tutorial_text = R.string.tutorial_explanation

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = image_compose,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(title),
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)

            )

            Text(
                text = stringResource(jepack_text),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Justify
            )

            Text(
                text = stringResource(tutorial_text),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun articuloPreview() {
    articulo()
}
