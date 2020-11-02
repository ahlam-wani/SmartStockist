package com.smartstockist.app.ui.email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartstockist.app.data.model.User
import com.smartstockist.app.data.remote.ApiClient
import com.smartstockist.app.data.remote.ApiService
import com.smartstockist.app.data.remote.NetworkState
import kotlinx.coroutines.launch

class EmailViewModel:ViewModel() {
    private val emailService by lazy {
        ApiClient.retrofit.create(ApiService::class.java)
    }
    private val _emailState=MutableLiveData<NetworkState<List<User>>>()
    val emailState:LiveData<NetworkState<List<User>>> get() = _emailState
    fun getCompany(email:String){
        _emailState.value=NetworkState.Loading()
        viewModelScope.launch {
            try {
                val response = emailService.companyList(email)
                if (404 == response.code && "error".equals(response.status,true)){
                    println(response.data)
                    _emailState.value=NetworkState.Error(response.errors?.firstOrNull()!!)
                }
             //  _emailState.value=NetworkState.Success(response.data,response.message)
            }catch (ex:Exception){
                        println("exxxception $ex")
                //_emailState.value= error(ex)
            }
        }
    }
}