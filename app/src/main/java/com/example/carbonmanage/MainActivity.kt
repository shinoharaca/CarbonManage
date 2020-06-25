package com.example.carbonmanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity;
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentLogin = Intent(applicationContext, HomeActivity::class.java )
        val intentSignUp = Intent(applicationContext, HomeActivity::class.java)
        val intentShared = Intent(applicationContext, HomeActivity::class.java)

        btLogin.setOnClickListener { startActivity(intentLogin) }

    }
}