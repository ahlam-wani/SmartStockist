package com.smartstockist.app.ui.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smartstockist.app.databinding.ActivityClientBinding
import com.smartstockist.app.utils.SessionManager

class ClientActivity : AppCompatActivity() {
    private val binding:ActivityClientBinding by lazy {
        ActivityClientBinding.inflate(layoutInflater)
    }
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sessionManager= SessionManager(this)
        Toast.makeText(this,"token= ${sessionManager.getToken()}",Toast.LENGTH_SHORT).show()

        binding.toolbar.ivBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}