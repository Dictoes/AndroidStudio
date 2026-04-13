package com.example.vaultbyte

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vaultbyte.databinding.ActivityDashboardScreenBinding

class DashboardScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityDashboardScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load username from SharedPreferences
        val prefs = getSharedPreferences("VaultByte", MODE_PRIVATE)
        val username = prefs.getString("username", "User")?.trim()
        binding.tvWelcome.text = "Welcome, $username!"

        // Profile button click
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileScreenActivity::class.java))
        }

        // Logout button click
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            // DO NOT CLEAR SharedPreferences
        }
    }
}