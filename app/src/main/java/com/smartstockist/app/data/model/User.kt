package com.smartstockist.app.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Id")
    val Id:String,
    @SerializedName("CompanyName")
    val CompanyName:String,
    @SerializedName("ExpiresOn")
    val ExpiresOn:String
)