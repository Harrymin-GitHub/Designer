package com.minhr.design

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minhr.design.module_core.ui.home.ACT_Home

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ACT_Home::class.java))
        finish()
    }
}
