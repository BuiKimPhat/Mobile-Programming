package com.example.anythingapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun MusicScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun MovieScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Movie View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun TipScreen(){
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

@Composable
fun PortfolioScreen(){
    val status = remember{
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = MaterialTheme.colors.background
    ) {
        Card (
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                CreateInfo()
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = {
                    status.value = !status.value
                }){
                    Text("Portfolio", style = MaterialTheme.typography.button)
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Divider()
                if (status.value)
                    Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
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

@Composable
fun CreateInfo(){
    Column(modifier = Modifier.padding(3.dp)) {
        Text(text = "Bui Kim Phat",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant)

        Text(text = "Android programmer",
            modifier = Modifier.padding(3.dp))

        Text(text = "@themeCompose",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(3.dp))

    }
}

@Composable
fun CreateImageProfile() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ){
        Image(painter = painterResource(id = R.drawable.ic_baseline_person_24),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop)
    }
}

@Composable
fun Portfolio(data: List<String>){
    LazyColumn{
        items(data){
                item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    CreateImageProfile()
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great project", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}