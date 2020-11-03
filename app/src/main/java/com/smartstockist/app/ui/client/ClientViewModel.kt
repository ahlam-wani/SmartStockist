package com.smartstockist.app.ui.client

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartstockist.app.data.model.GetClients
import com.smartstockist.app.data.remote.Api
import com.smartstockist.app.data.remote.ApiResponse
import com.smartstockist.app.data.remote.ApiService
import com.smartstockist.app.data.remote.NetworkState
import kotlinx.coroutines.launch
import retrofit2.*
import java.lang.Exception

class ClientViewModel:ViewModel(){
       private  val api by lazy{
           Api.retrofit.create(ApiService::class.java)
       }

    private val _clientState=MutableLiveData<NetworkState<List<GetClients>>>()
    val clientState:MutableLiveData<NetworkState<List<GetClients>>> get() = _clientState
    fun getClients(){
        _clientState.value=NetworkState.Loading()
        viewModelScope.launch {
            try {
                //val response=api.getClients()
                 val call:retrofit2.Call<List<GetClients>> =api.Clients()
                call.enqueue(object :Callback<List<GetClients>>{
                    override fun onResponse(
                        call: Call<List<GetClients>>,
                        response: Response<List<GetClients>>
                    ) {
                        println("response ${response.body()}")
                        if (response.isSuccessful){
                            _clientState.value=NetworkState.Success(response.body())
                            println("response ${response.body()}")
                        }

                    }

                    override fun onFailure(call: Call<List<GetClients>>, t: Throwable) {
                       println("error while etting data ${t.message}")
                    }

                })
                //println(" data is ${response.body.toString()}")
                //_clientState.value = NetworkState.Success(response.clients.toList())

            }catch (ex:Exception){
                _clientState.value=NetworkState.Error(ex.message.toString())
                println(" Exception is $ex")
            }
        }
    }


}