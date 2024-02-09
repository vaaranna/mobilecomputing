package com.example.hw2

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

data class Message(val author: String, val body: String)


@Composable
    fun MessageCard(msg: Message, userViewModel: UserViewModel) {
        val user by userViewModel.user.observeAsState()

        Row(modifier = Modifier.padding(all = 8.dp)) {
            val image = user?.image
            if (image?.isNotEmpty() == true) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    //  contentDescription = stringResource(R.string.description),
                    //    contentScale = ContentScale.CropImage,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.6.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.6.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { // this remembers if the message is expanded or not
                mutableStateOf(false)
            }
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                label = "",
            )
            // toggles the isExpanded variable when we click on this column
            Column (modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = user?.username ?: "default user",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // if the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }



    /**
     * SampleData for Jetpack Compose Tutorial
     */
    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "Lexi",
                "Test...Test...Test..."
            ),
            Message(
                "Lexi",
                """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
            ),
            Message(
                "Lexi",
                """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
            ),
            Message(
                "Lexi",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Lexi",
                """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
            ),
            Message(
                "Lexi",
                "It's available from API 21+ :)"
            ),
            Message(
                "Lexi",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Lexi",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Lexi",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Lexi",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Lexi",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Lexi",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Lexi",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }

    @Composable
    fun Conversation(
        messages: List<Message>,
        navController: NavHostController,
        viewModel: UserViewModel) {
     //   val user by userViewModel.user.observeAsState()

        LazyColumn {
            items(messages) { message ->
                MessageCard(message, viewModel)
            }
        }
            Button(
                onClick = { navController.navigate("second") }
            ) {
                Text("My profile")
            }
        }


    //@Composable
    //fun Preview(navController: NavController) {

