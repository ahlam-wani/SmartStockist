package com.smartstockist.app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smartstockist.app.data.remote.NetworkState
import com.smartstockist.app.databinding.ActivityLoginBinding
import com.smartstockist.app.ui.dashboard.DashboardActivity
import com.smartstockist.app.utils.CONNECTION_ERROR
import com.smartstockist.app.utils.Session
import com.smartstockist.app.utils.SessionManager

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sessionManager=SessionManager(this)
        loginViewModel.loginState.observe(this,::onLogin)
        binding.btnLogin.setOnClickListener {
            val username=binding.etUsername.text.toString().trim()
            val password=binding.etPassword.text.toString().trim()
           if(validate(username,password)){
               //  var user=LoginModelClass(username,password,"319d0f20-5d79-416f-b698-41e3aa3a6b73")
               loginViewModel.login(username,password,"319d0f20-5d79-416f-b698-41e3aa3a6b73")
           }
        }
    }
    fun onLogin(s:NetworkState<Any>){
           if (s is NetworkState.Loading<Any>){
               Toast.makeText(this,"Loading Please wait",Toast.LENGTH_SHORT).show()
               return
           }
        when(s) {
            is NetworkState.Success<Any> -> {
                     val user=s.data.toString()
                   sessionManager.setSession(Session(user))
                Toast.makeText(this, "sucess $user", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            is NetworkState.Error<Any> -> {
                Toast.makeText(this,"erroe",Toast.LENGTH_SHORT).show()
            }
            is NetworkState.Failure<Any>->
            {
                Toast.makeText(this, CONNECTION_ERROR,Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validate(username:String,password:String):Boolean {
        if (username.isEmpty()) {
           Toast.makeText(this,"Enter username",Toast.LENGTH_SHORT).show()
            return false
        } else if (password.isEmpty()) {
            Toast.makeText(this,"eNTER PASSWORD",Toast.LENGTH_SHORT).show()
            return false
        }else
             return true
    }

}