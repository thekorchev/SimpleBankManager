package org.hyperskill.simplebankmanager

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Balance.balance = intent.extras?.getDouble("balance") ?: 100.0
    }
}