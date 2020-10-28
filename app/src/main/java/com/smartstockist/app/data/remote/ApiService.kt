package com.smartstockist.app.data.remote

import com.smartstockist.app.data.model.User
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ApiService {
    @GET("/account/company?")
    suspend fun companyList(@Query("email") email:String):ApiResponse<List<User>>
}