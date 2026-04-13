package com.example.vaultbyte

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vaultbyte.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences instance
        val prefs = getSharedPreferences("VaultByte", MODE_PRIVATE)

        // Auto-fill email if it was previously saved
        val savedEmail = prefs.getString("email", "")
        val savedPassword = prefs.getString("password", "")

        binding.etEmail.setText(savedEmail)
        binding.etPassword.setText(savedPassword)

        // LOGIN BUTTON
        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.etEmail.text.toString().trim()
            val inputPassword = binding.etPassword.text.toString()

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check credentials against saved SharedPreferences
            if (inputEmail == savedEmail && inputPassword == savedPassword) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                // Save last session (optional)
                prefs.edit().putBoolean("isLoggedIn", true).apply()

                // Go to dashboard
                startActivity(Intent(this, DashboardScreenActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // GO TO REGISTER SCREEN
        binding.tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterScreenActivity::class.java))
            finish()
        }
    }
}