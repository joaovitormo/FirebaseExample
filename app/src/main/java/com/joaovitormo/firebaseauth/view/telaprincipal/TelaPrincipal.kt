package com.joaovitormo.firebaseauth.view.telaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.joaovitormo.firebaseauth.R
import com.joaovitormo.firebaseauth.databinding.ActivityFormLoginBinding
import com.joaovitormo.firebaseauth.databinding.ActivityTelaPrincipalBinding
import com.joaovitormo.firebaseauth.view.formlogin.FormLogin

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDeslogar.setOnClickListener {
            auth.signOut()
            voltarTelaLogin()

        }
    }

    private fun voltarTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}