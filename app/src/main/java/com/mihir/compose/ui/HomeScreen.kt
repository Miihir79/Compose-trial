package com.mihir.compose.ui

import android.widget.AdapterView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mihir.compose.BottomNavBarItem
import com.mihir.compose.Features
import com.mihir.compose.R
import com.mihir.compose.standardQuadFromTo
import com.mihir.compose.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()){
         Column {
             GreetingSection()
             ChipSection(chips = listOf("Sweet Sleep","Insomnia","Depression"))
             CurrentMeditation()
             FeatureSection(features = listOf(
                 Features(
                     title = "Sleep meditation",
                     R.drawable.ic_headphone,
                     BlueViolet1,
                     BlueViolet2,
                     BlueViolet3
                 ),
                 Features(
                     title = "Tips for sleeping",
                     R.drawable.ic_videocam,
                     LightGreen1,
                     LightGreen2,
                     LightGreen3
                 ),
                 Features(
                     title = "Night island",
                     R.drawable.ic_headphone,
                     OrangeYellow1,
                     OrangeYellow2,
                     OrangeYellow3
                 ),
                 Features(
                     title = "Calming sounds",
                     R.drawable.ic_headphone,
                     Beige1,
                     Beige2,
                     Beige3
                 )
             ))
         }

        BottomMenu(items = listOf(
            BottomNavBarItem("Home", R.drawable.ic_home),
            BottomNavBarItem("Meditate", R.drawable.ic_bubble),
            BottomNavBarItem("Sleep", R.drawable.ic_moon),
            BottomNavBarItem("Music", R.drawable.ic_music),
            BottomNavBarItem("Profile", R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun GreetingSection(name:String="Mihir"){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)){
        Column(verticalArrangement = Arrangement.Center) {
            Text(text="Good Morning,$name", style = MaterialTheme.typography.h2)
            Text(text="We wish you have a good day", style = MaterialTheme.typography.body1)

        }
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_search_24 ),
            contentDescription = "Search icon",
            tint = Color.White,
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips:List<String>
){
    var selectedChipIndex by remember{
        mutableStateOf(0)

    }

    LazyRow{
        items(chips.size){
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .background(
                    if (selectedChipIndex == it) Color(0xff505cf3)
                    else Color(0xff566894)
                )
                .padding(15.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = chips[it], color = Color(0xffeeeeee))
            }
        }
    }
}

@Composable
fun BottomMenu(items: List<BottomNavBarItem>,
                modifier: Modifier= Modifier,
                colorSelected:Color = Color(0xff505cf3),
                colorNotSelected:Color = Color.White,
                colorText:Color = AquaBlue,
                initalItemSelected:Int =0) {
 var selectItemIndex by remember {
     mutableStateOf(initalItemSelected)
 }
    Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically
    , modifier = modifier
            .fillMaxWidth()
            .background(Color(0xff06164c))
            .padding(15.dp)) {
        items.forEachIndexed{index, item ->
             BottomMenuItem(item = item,isSelected = index == selectItemIndex ,
                 colorSelected =colorSelected,colorNotSelected = colorNotSelected , colorText = colorText) {
                    selectItemIndex = index
             }
        }
    }


}

@Composable
fun BottomMenuItem(item:BottomNavBarItem,
                    isSelected:Boolean=false,
                   colorSelected:Color = Color(0xff505cf3),
                   colorNotSelected:Color = Color.White,
                   colorText:Color = AquaBlue,
                   onItemClick:()-> Unit
) {
Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.clickable {
    onItemClick
}) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(if (isSelected) colorSelected else Color.Transparent)
        .padding(10.dp)){
        Icon(painter = painterResource(id = item.iconId),
            contentDescription = item.title,
            tint = if (isSelected) colorNotSelected else colorText,
            modifier = Modifier.size(20.dp))
    }
    Text(
        text = item.title,
        color = if(isSelected) colorNotSelected else colorText
    )
}
}

@Composable
fun CurrentMeditation(color: Color = Color(0xFFE76161)) {
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = "Daily Thought", style = MaterialTheme.typography.h2)
            Text(text = "Mediation . 3-10 min", style = MaterialTheme.typography.h2, color = Color(0xffeeeeee))
        }
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xff505cf3))
            .padding(10.dp)){
            Icon(painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                contentDescription ="Play",
                tint = Color.White,
            modifier = Modifier.size(16.dp))
        }
       
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Features>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Features", style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(features.size){
                FeatureItem(features = features[it])
            }
        }
    }

}

@Composable
fun FeatureItem(features: Features) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium color path
        val mediumColorPoint1= Offset(0f,height * 0.3f)
        val mediumColorPoint2= Offset(width * 0.1f,height * 0.35f)
        val mediumColorPoint3= Offset(width * 0.4f,height * 0.05f)
        val mediumColorPoint4= Offset(width * 0.75f,height * 0.7f)
        val mediumColorPoint5= Offset(width * 1.4f,-height.toFloat())

        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x,mediumColorPoint1.y)
            //custom util
            standardQuadFromTo(mediumColorPoint1,mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2,mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3,mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4,mediumColorPoint5)
            lineTo(
                width.toFloat() + 100f, height.toFloat() +100f
            )
            lineTo(
                -100f, height.toFloat() +100f
            )
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        
        Canvas(modifier = Modifier.fillMaxHeight()){
            drawPath(
                path = mediumColorPath,
                color= features.mediumColor
            )
            drawPath(path = lightColoredPath,color = features.lightColor)
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){
            Text(text = features.title, style = MaterialTheme.typography.h2,lineHeight = 26.sp, modifier = Modifier.align(
                Alignment.TopStart)
            )
            Icon(
                    painter = painterResource(id = features.iconId) , contentDescription = features.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text("Start",fontSize = 14.sp,color = TextWhite,fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable {

                }
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xff505cf3))
                .padding(vertical = 6.dp, horizontal = 15.dp))
        }

    }
}