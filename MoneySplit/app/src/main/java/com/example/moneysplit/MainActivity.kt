package com.example.moneysplit

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneysplit.ui.theme.MoneySplitTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneySplitTheme {
                var moneyEach by remember{
                    mutableStateOf(0.0f)
                }
                Column() {
                    TotalDisplay(money = moneyEach)
                    MoneyControl() {
                            newEach -> moneyEach = newEach
                    }
                }
            }
        }
    }
}

@Composable
fun TotalDisplay(money: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(32.dp),
        color = Color.LightGray,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Total Per Person", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "$" + String.format("%.2f", money), fontWeight = FontWeight.Bold, fontSize = 32.sp)
        }
    }
}

@Composable
fun MoneyControl(
    changeEach: (Float) -> Unit
) {
    var total by remember {
        mutableStateOf("")
    }
    var isShow by remember {
        mutableStateOf(false)
    }
    var people by remember{
        mutableStateOf(1)
    }
    var tip by remember{
        mutableStateOf(0.0f)
    }
    var tipSlide by remember{
        mutableStateOf(0.0f)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))) {
        Column() {
            Row(){
                OutlinedTextField(
                    value = total,
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_attach_money_24),
                                            contentDescription = "Dollar") },
                    onValueChange = {
                        total = it
                        if (it.isNotEmpty()) {
                            isShow = true
                            tip = ((tipSlide*100).roundToInt())*(total.toFloat())/100
                            changeEach((total.toFloat()+tip)/people)
                        } else isShow = false
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text("Enter Bill") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            if (isShow) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Split", modifier = Modifier.width(80.dp), fontSize = 16.sp)
                    IconButton(onClick = {
                        if (people > 1) people -= 1
                        tip = ((tipSlide*100).roundToInt())*(total.toFloat())/100
                        changeEach((total.toFloat()+tip)/people)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_24), contentDescription = "-1")
                    }
                    Text(text = people.toString(), fontSize = 16.sp)
                    IconButton(onClick = {
                        people += 1
                        tip = ((tipSlide*100).roundToInt())*(total.toFloat())/100
                        changeEach((total.toFloat()+tip)/people)
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "+1")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Tip", modifier = Modifier.width(120.dp), fontSize = 16.sp)
                    Text(text = "$" + String.format("%.2f", tip), fontSize = 16.sp)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(text = String.format("%.0f", tipSlide*100) + " %", fontSize = 16.sp)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Slider(value = tipSlide, onValueChange = {
                        tipSlide = it
                        tip = ((tipSlide*100).roundToInt())*(total.toFloat())/100
                        changeEach((total.toFloat()+tip)/people)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoneySplitTheme {
    }
}