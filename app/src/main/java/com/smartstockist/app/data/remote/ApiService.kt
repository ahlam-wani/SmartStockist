package com.smartstockist.app.data.remote

import com.smartstockist.app.data.model.LoginModelClass
import com.smartstockist.app.data.model.User
import retrofit2.http.*

interface  ApiService {
    @GET("account/company/")
    suspend fun companyList(@Query("email") email:String):ApiResponse<List<User>>

    @POST("account/token")
    suspend fun token(@Body user:LoginModelClass):ApiResponse<LoginModelClass>
}