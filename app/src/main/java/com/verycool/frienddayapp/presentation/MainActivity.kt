package com.verycool.frienddayapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.verycool.frienddayapp.presentation.ui.composables.navigation.Navigation
import com.verycool.frienddayapp.presentation.ui.theme.FriendDayAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics

        enableEdgeToEdge()
        setContent {
            FriendDayAppTheme {
                Navigation()
            }
        }

        LogEvent.logEvent(
            analytics,
            "MainActivity",
            "Created"
        )
    }

    override fun onStart() {
        super.onStart()
        LogEvent.logEvent(
            analytics,
            "MainActivity",
            "Started"
        )
    }

    override fun onResume() {
        super.onResume()
        LogEvent.logEvent(
            analytics,
            "MainActivity",
            "Resume"
        )
    }
}

object LogEvent{
    fun logEvent(
        analytics: FirebaseAnalytics,
        id:String,
        name:String
    ){
        analytics.logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT, bundleOf(
                Pair(FirebaseAnalytics.Param.ITEM_ID, id),
                Pair(FirebaseAnalytics.Param.ITEM_NAME, name),
                Pair(FirebaseAnalytics.Param.CONTENT_TYPE, "text")
            )
        )
    }
}