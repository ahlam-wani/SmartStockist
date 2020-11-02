package com.smartstockist.app.data.model

import com.google.gson.annotations.SerializedName

data class LoginModelClass(@SerializedName("username")
                           val username: String?,
                           @SerializedName("password")
                           val password: String?,
                           @SerializedName("licenseid")
                           val licenseid: String?,
                           @SerializedName("access_token")
                           val access_token: String?,
                            @SerializedName("error")
                            val error: String?)
