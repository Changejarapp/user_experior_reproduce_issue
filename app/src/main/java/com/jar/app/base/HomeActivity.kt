package com.jar.app.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.userexperior.UserExperior
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUserExperior(this)
    }
}

private fun initUserExperior(context: Context) {
    Log.d("SEEHERE", "initUserExperior: ")
    UserExperior.startRecording(context,"ee06fb08-7564-46e6-a6ac-4eac0f21ee44")
}
