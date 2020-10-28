package com.smartstockist.app.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartstockist.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (binding.etUsername.text.toString().isEmpty()) {
            binding.etUsername.setError("enter valid username")
            binding.tilUsername.requestFocus()
        } else if (binding.etPassword.text.toString().isEmpty()) {
               binding.etUsername.setError("enter valid password")
              binding.tilPassword.requestFocus()
        }
    }

}