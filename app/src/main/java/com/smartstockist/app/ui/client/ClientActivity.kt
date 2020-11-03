package com.smartstockist.app.ui.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartstockist.app.data.model.GetClients
import com.smartstockist.app.data.remote.NetworkState
import com.smartstockist.app.databinding.ActivityClientBinding
import com.smartstockist.app.utils.CONNECTION_ERROR
import com.smartstockist.app.utils.SessionManager
import com.smartstockist.app.utils.token

class ClientActivity : AppCompatActivity() {
    private val binding: ActivityClientBinding by lazy {
        ActivityClientBinding.inflate(layoutInflater)
    }
    lateinit var sessionManager: SessionManager
    private val viewModel by lazy {
        ViewModelProvider(this).get(ClientViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        token = sessionManager.getToken().toString()
        binding.toolbar.ivBack.setOnClickListener {
            super.onBackPressed()
        }
         viewModel.getClients()
        viewModel.clientState.observe(this, ::onFetchClients)

    }

    fun onFetchClients(state: NetworkState<List<GetClients>>) {
        if (state is NetworkState.Loading) {
            binding.progressBar.visibility = View.VISIBLE
            return
        }
        binding.progressBar.visibility = View.GONE
        when (state) {
            is NetworkState.Success -> {
                setAdapter(state.data, state.message)
            }
            is NetworkState.Error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            is NetworkState.Failure ->
                Toast.makeText(this, CONNECTION_ERROR, Toast.LENGTH_SHORT).show()
        }


    }
    private fun setAdapter(data: List<GetClients>?, msg: String?) {
        if (data.isNullOrEmpty()) {
            binding.apply {
                Toast.makeText(this@ClientActivity, "$msg", Toast.LENGTH_SHORT).show()
            }
            return

        }
        binding.rvNotificatons.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNotificatons.adapter = ClientAdapter(data)
    }
}