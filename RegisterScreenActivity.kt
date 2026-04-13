package com.example.vaultbyte

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vaultbyte.databinding.ActivityRegisterScreenBinding

class RegisterScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences instance
        val prefs = getSharedPreferences("VaultByte", MODE_PRIVATE)

        // REGISTER BUTTON
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val firstName = binding.etFirstName.text.toString().trim()
            val middleName = binding.etMiddleName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            // 1️⃣ Required fields check
            if (username.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2️⃣ Email format validation
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3️⃣ Password match validation
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4️⃣ Save to SharedPreferences (force commit to ensure save)
            val editor = prefs.edit()
            editor.putString("username", username)
            editor.putString("firstName", firstName)
            editor.putString("middleName", middleName)
            editor.putString("lastName", lastName)
            editor.putString("email", email)
            editor.putString("password", password)
            val saved = editor.commit() // synchronous save
            if (!saved) {
                Toast.makeText(this, "Error saving data. Try again.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Debugging: print saved data to Logcat
            println("Saved email: ${prefs.getString("email","")}")
            println("Saved password: ${prefs.getString("password","")}")

            Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_SHORT).show()

            // 5️⃣ Navigate to Login Screen
            startActivity(Intent(this, LoginScreenActivity::class.java))
            finish()
        }

        // GO TO LOGIN TEXT
        binding.tvGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginScreenActivity::class.java))
            finish()
        }
    }
}