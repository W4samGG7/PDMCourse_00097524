package LemonadeApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pdm_00097524.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlin.random.Random

@Composable
fun LemonadeApp (modifier: Modifier = Modifier){
    var  currentStep by rememberSaveable { mutableIntStateOf(1) }
    var tapsNeeded by rememberSaveable { mutableIntStateOf(Random.nextInt(2,5)) }
    var currentTaps by rememberSaveable { mutableIntStateOf(0) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val buttonText = when (currentStep){
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }

    val onImageClick:() ->Unit = {
        if(currentStep == 2){
            currentTaps ++
            if(currentTaps >= tapsNeeded) {
                currentStep++
                currentTaps = 0
            }
        } else if (currentStep == 4){
            tapsNeeded = Random.nextInt(2,5)
            currentStep = 1
        } else{
            currentStep++
        }
    }

    Column(modifier = modifier) {
        TopBar()
        LemonadeContainer(
            imageResource,
            buttonText,
            onClickImage = onImageClick,
            currentTaps,
            tapsNeeded
        )
    }
}

@Composable
fun TopBar(){
    Box(
        modifier = Modifier.fillMaxWidth().background(Color.Yellow).height(50.dp)
    ){
        Text(
            text="Lemonade App",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun LemonadeContainer(
    imageResource: Int,
    text: String,
    onClickImage: () -> Unit,
    currentTaps: Int,
    taps: Int
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClickImage,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2))
        ){
            Image(
                painter = painterResource(id= imageResource),
                contentDescription = text
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = text
        )
        if(imageResource == R.drawable.lemon_squeeze){
            Text(text = "Numero de pasos faltantes: ${taps-currentTaps}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview(){
    LemonadeApp()
}