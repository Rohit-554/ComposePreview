package io.jadu.composetesting

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MembersList() {
    val userList = listOf(
        User("Sudharsan Parthsarathi", "Designer", JobType.DESIGNER),
        User("Hiren Giridharial Parekh", "Developer", JobType.DEVELOPER),
        User("Lalit Kamble", "Manager", JobType.MANAGER)
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            // Search bar

            MySearchBar(
                text = "",
                onTextChange = {},
                placeHolder = "Search",
                onCloseClicked = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            Box(modifier = Modifier.fillMaxWidth().padding()) {
                Text(text = "People" , style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Medium), modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 16.dp)
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )

            // Member count
            MemberCount("3 MEMBERS")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )

            // Lazy column
            LazyColumn(modifier = Modifier.padding()) {
                items(userList.size) { index ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()) {
                        UserProfileUi(userList[index])
                    }
                    // Horizontal line
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}


@Composable
fun MySearchBar(
    modifier : Modifier = Modifier,
    text : String,
    onTextChange : (String) -> Unit,
    placeHolder : String,
    onCloseClicked : () -> Unit,
    onMicClicked : () -> Unit
){
    OutlinedTextField(
        shape = CircleShape,
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = placeHolder
            )
        },
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = modifier.size(22.dp)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                if (text.isNotBlank()){
                    onCloseClicked()
                }else{
                    onMicClicked()
                }
            }) {
                if (text.isNotBlank()){
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = modifier.size(22.dp)
                    )
                }else {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = modifier.size(22.dp)
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()

    )
}

@Composable
fun MemberCount(member:String){
    Text(text = member, style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontWeight = FontWeight.Medium), modifier = Modifier.padding(start = 32.dp, top = 12.dp))
}

@Composable
fun UserProfileUi(user:User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.LightGray, CircleShape)   // add a border (optional)
        )
        UserDetails(userName = user.name, userJobDescription = user.jobDescription, jobType = user.jobType)
    }
}

@Composable
fun UserDetails(userName: String, userJobDescription: String, jobType: JobType) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(
            text = userName,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Text(text = userJobDescription, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        Spacer(modifier = Modifier.size(4.dp))
        DesignerChip(jobType = jobType)
    }
}

@Composable
fun DesignerChip(jobType: JobType) {

    when (jobType) {
        JobType.DESIGNER -> {
            ChipDesign(icon = R.drawable.baseline_sports_volleyball_24, text = "Design")
        }

        JobType.DEVELOPER -> {
            ChipDesign(icon = R.drawable.baseline_sports_volleyball_24, text = "Developer", color = Color.LightGray)
        }

        JobType.MANAGER -> {
            ChipDesign(icon = R.drawable.baseline_sports_volleyball_24, text = "Collections and Recoveries", color = Color.Green)
        }


        else -> {}
    }

}

@Composable
fun ChipDesign(icon: Int, text: String, color: Color = Color.LightGray) {
    Row(
        modifier = Modifier.background(color, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(end = 8.dp, start = 2.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
@Preview
fun UserProfileUiPreview() {
    UserProfileUi(User("Sudharsan Parthsarathi", "Designer", JobType.DESIGNER))
}

enum class JobType {
    DESIGNER, DEVELOPER, MANAGER
}

data class User(val name: String, val jobDescription: String, val jobType: JobType)
/*
@Composable
@Preview
fun MembersListPreview() {
    MembersList()
}*/
