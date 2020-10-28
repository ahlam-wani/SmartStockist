package com.smartstockist.app.ui
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.smartstockist.app.databinding.ActivitySplashBinding
import com.smartstockist.app.ui.email.EmailActivity

class SplashActivity : AppCompatActivity() {
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val binding : ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        handler = Handler()
        runnable = Runnable {
            startActivity(Intent(this, EmailActivity::class.java))
            finish() }
        }


    override fun onStart() {
        super.onStart()
        handler?.postDelayed(runnable, 1500)
    }
}