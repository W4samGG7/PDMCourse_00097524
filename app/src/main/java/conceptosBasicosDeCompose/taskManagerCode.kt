package conceptosBasicosDeCompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pdm_00097524.R
@Composable
fun taskManager(modifier: Modifier = Modifier){
    val image_task = painterResource(R.drawable.ic_task_completed)
    val message_task = R.string.task_message
    val congrats_task = R.string.task_congrats

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image_task,
                contentDescription = null
            )
            Text(
                text = stringResource(message_task),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=24.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(congrats_task),
                fontSize = 16.sp
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun taskManagerPreview(){
    taskManager()
}
