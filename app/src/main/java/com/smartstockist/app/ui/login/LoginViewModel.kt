package com.smartstockist.app.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartstockist.app.data.model.LoginModelClass
import com.smartstockist.app.data.remote.ApiResponse
import com.smartstockist.app.data.remote.ApiService
import com.smartstockist.app.data.remote.LoginApiClient
import com.smartstockist.app.data.remote.NetworkState
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel:ViewModel() {
           private val loginService by lazy {
               LoginApiClient.retrofit.create(ApiService::class.java)
           }
    private val _loginState=MutableLiveData<NetworkState<Any>>()
    val loginState:LiveData<NetworkState<Any>> get()=_loginState

    fun login(username:String,password:String,licenseid:String){
        _loginState.value=NetworkState.Loading()
        viewModelScope.launch {
            try {
                 val response=loginService.token(LoginModelClass(username,password,licenseid ,"",""))

                if (400 == response.code && "Bad Request".equals(response.status,true)){
                    _loginState.value=NetworkState.Error(response.errors!![0],response.code.toString())
                    println("erroe is ${response.code} ${response.data}")
                }
                else{
                    _loginState.value=NetworkState.Success(response.access_token)
                    println("data is ${response.access_token} ${response.errors} ${response.status}")
                }
            }catch (ex:Exception){
                //_loginState.value=NetworkState.Failure(ex.toString())
                println("exception is $ex ")
            }
        }
    }


}