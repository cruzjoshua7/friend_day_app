package com.verycool.frienddayapp.presentation.ui.composables.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.verycool.frienddayapp.R
import com.verycool.frienddayapp.presentation.ui.composables.navigation.Screen
import com.verycool.frienddayapp.viewmodel.FriendDayViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FriendDayViewModel
) {

    val user by viewModel.selectedUser.collectAsState()
    val context = LocalContext.current

    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
        // TODO: upload new profile image & update Firestore if desired
    }

    if (user == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .clickable {
                        launcher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                when {
                    profileImageUri != null -> {
                        Image(
                            painter = rememberAsyncImagePainter(profileImageUri),
                            contentDescription = "New Profile Picture",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    user!!.profileImage.isNotBlank() -> {
                        AsyncImage(
                            model = user!!.profileImage,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    else -> {
                        Image(
                            painter = painterResource(id = R.drawable.profile_image),
                            contentDescription = "Default Profile Picture",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }

            // Bottom-right icon (edit button, etc.)
            Image(
                painter = painterResource(id = R.drawable.edit_pencil), // Replace with your drawable
                contentDescription = "Edit Icon",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 0.dp, y = 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Detail Cards
        ProfileCard(
            icon = R.drawable.profile_image,
            text = "Username: ${user?.name ?: "Unknown"}",
            rightIcon = R.drawable.edit_pencil,
            onClick = { }
        )

        ProfileCard(
            icon = R.drawable.mail_icon,
            text = "Email: ${user?.uid ?: "No email"}", // If email isn't stored in User, update accordingly
            rightIcon = R.drawable.edit_pencil,
            onClick = { }
        )

        ProfileCard(
            icon = R.drawable.lock_icon,
            text = "Password",
            rightIcon = R.drawable.edit_pencil,
            onClick = { }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.logout()
                navController.navigate(Screen.LoginScreen.route){
                    popUpTo(Screen.ProfileScreen.route){inclusive = true}
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Logout", color = Color.White)
        }
    }
}

@Composable
fun ProfileCard(
    icon: Int,
    text: String,
    rightIcon: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Icon
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Middle Text
            Text(
                text = text,
                modifier = Modifier.weight(1f),
            )

            // Right Icon
            Image(
                painter = painterResource(id = rightIcon),
                contentDescription = "Right Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
}