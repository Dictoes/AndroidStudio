package com.example.vaultbyte

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileScreenActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var tvUsername: TextView
    private lateinit var tvFirstName: TextView
    private lateinit var tvMiddleName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var profileImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)


        btnBack = findViewById(R.id.btnBack)
        profileImage = findViewById(R.id.profileImage)
        tvUsername = findViewById(R.id.tvUsername)
        tvFirstName = findViewById(R.id.tvFirstName)
        tvMiddleName = findViewById(R.id.tvMiddleName)
        tvLastName = findViewById(R.id.tvLastName)
        tvEmail = findViewById(R.id.tvEmail)


        val prefs = getSharedPreferences("VaultByte", MODE_PRIVATE)
        val username = prefs.getString("username", "")
        val firstName = prefs.getString("firstName", "")
        val middleName = prefs.getString("middleName", "")
        val lastName = prefs.getString("lastName", "")
        val email = prefs.getString("email", "")


        tvUsername.text = username
        tvFirstName.text = firstName
        tvMiddleName.text = middleName
        tvLastName.text = lastName
        tvEmail.text = email


        btnBack.setOnClickListener {
            finish()
        }
    }
}