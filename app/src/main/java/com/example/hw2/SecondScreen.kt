package com.example.hw2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


// second screenin alustus

    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun SecondScreen(navController: NavController, userViewModel: UserViewModel) {
        val user by userViewModel.user.observeAsState()

    var imageUri by remember {
        mutableStateOf(user?.image?: "")
    }
    var username by remember { mutableStateOf(user?.username?:"") }

        val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            try {
                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                val contentResolver = context.contentResolver
                contentResolver.takePersistableUriPermission(uri, takeFlags)
                imageUri = uri.toString()
            } catch (e: Exception) {
                Log.e("main activity", "error granting persistable permission", e)
            }

        }
    }
    Column(
        modifier = Modifier.padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Button(
            onClick = { navController.popBackStack() },
            // modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conversation")
        }

            AsyncImage(
                model = imageUri,
                contentDescription = null,
                //  contentDescription = stringResource(R.string.description),
                //    contentScale = ContentScale.CropImage,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.6.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    .clickable {
                        launcher.launch(arrayOf("image/*"))
                    }
            )

        Text("My profile")
        OutlinedTextField(
            value = username,
            onValueChange = { newText -> username = newText },
            label = { Text("my name") }
        )
        Button(
            onClick = {
                if (username.isNotEmpty() && imageUri.isNotEmpty()) {
                    userViewModel.saveUserProfile(username, imageUri)
                }
            }
        ) {
         //   modifier = Modifier.padding(vertical = 8.dp)
            Text("Save your profile")
        }
    }
}
// Launch the photo picker and let the user choose only images.

