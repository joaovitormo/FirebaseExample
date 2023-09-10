package com.joaovitormo.firebaseauth.view.telaprincipal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joaovitormo.firebaseauth.R
import com.joaovitormo.firebaseauth.databinding.ActivityFormLoginBinding
import com.joaovitormo.firebaseauth.databinding.ActivityTelaPrincipalBinding
import com.joaovitormo.firebaseauth.view.formlogin.FormLogin

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btDeslogar.setOnClickListener {
            auth.signOut()
            voltarTelaLogin()

        }

        binding.btGravarDadosDB.setOnClickListener {
            val usuariosMap = hashMapOf(
                "nome" to "Joao",
                "sobrenome" to "Oliveira",
                "idade" to 22
            )

            db.collection("Usuários").document("Joao")
                .set(usuariosMap).addOnCompleteListener {
                    Log.d("db", "Sucesso ao salvar os dados do usuário!")
                }.addOnFailureListener {
                    Log.d("db", "Erro ao salvar os dados do usuário!")
                }
        }

        binding.btLerDadosDB.setOnClickListener {
            db.collection("Usuários").document("Joao")
                .addSnapshotListener { documento, error ->
                    if (documento != null) {
                        Log.d("db", documento.toString())
                        val idade = documento.getLong("idade")
                        binding.txtResultado.text = idade.toString()
                        //binding.txtResultado.text = documento.getString("sobrenome")
                    }
                }
        }
        binding.btAtualizarDadosDB.setOnClickListener {
            val usuariosMap = hashMapOf(
                "nome" to "Joao",
                "sobrenome" to "Oliveira",
                "idade" to 22
            )

            db.collection("Usuários").document("Joao")
                .update("sobrenome", "Mendes").addOnCompleteListener {
                    Log.d("db_update", "Sucesso ao atualizar os dados do usuário!")
                }
        }
    }

    private fun voltarTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}