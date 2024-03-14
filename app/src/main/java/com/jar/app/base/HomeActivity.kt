package com.jar.app.feature.home.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.userexperior.UserExperior


internal class HomeActivity :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUserExperior(this)
    }

}

private fun initUserExperior(context: Context) {
    Log.d("SEEHERE", "initUserExperior: ")
    UserExperior.startRecording(context,"ee06fb08-7564-46e6-a6ac-4eac0f21ee44")
}
