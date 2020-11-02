package com.smartstockist.app.ui.email

import android.content.Intent
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smartstockist.app.data.model.User
import com.smartstockist.app.data.remote.NetworkState
import com.smartstockist.app.databinding.ActivityEmailBinding
import com.smartstockist.app.ui.login.LoginActivity
import com.smartstockist.app.utils.CONNECTION_ERROR

class EmailActivity : AppCompatActivity() {
    private val bindingComponent:ActivityEmailBinding by lazy {
        ActivityEmailBinding.inflate(layoutInflater)
    }
        private val emailViewModel by lazy {
            ViewModelProvider(this).get(EmailViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingComponent.root)
        emailViewModel.emailState.observe(this, ::onSuccess)
         bindingComponent.btnNext.setOnClickListener {
                 if (bindingComponent.etEmail.text.toString().trim().isEmpty() || !isEmailValid(bindingComponent.etEmail.text.toString().trim())){
                     Toast.makeText(this,"Plz enter Valid Email",Toast.LENGTH_SHORT).show()
                     startActivity(Intent(this,LoginActivity::class.java))
                 }else{
                     emailViewModel.getCompany(bindingComponent.etEmail.text.toString().trim())
                 }
         }
    }
    private fun onSuccess(state:NetworkState<List<User>>){
        if(state is NetworkState.Loading){
            Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
            return
        }
        when(state){
            is NetworkState.Success ->{
                println("sucess"+state.message)
            }
            is NetworkState.Error ->{
                println("errot"+state.message)
            }
            is NetworkState.Failure ->{
                println(CONNECTION_ERROR)
            }
        }

    }
  private  fun isEmailValid(email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
}