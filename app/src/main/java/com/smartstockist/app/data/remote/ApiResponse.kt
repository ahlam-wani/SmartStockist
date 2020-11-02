package com.smartstockist.app.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.smartstockist.app.data.model.LoginModelClass
import okhttp3.RequestBody

data class ApiResponse<T>(
    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("Status Code")
    @Expose
    val status: String,

    @SerializedName("message")
    @Expose
    val message: String?,

    @SerializedName("")
    @Expose
    val data: LoginModelClass?,

    @SerializedName("Raw")
    @Expose
    val body: RequestBody?,

    @SerializedName("errors")
    @Expose
    val errors: List<String>?,

    @SerializedName("access_token")
    @Expose
    val access_token: String? = null,

    @Expose val refreshToken: String? = null
)
