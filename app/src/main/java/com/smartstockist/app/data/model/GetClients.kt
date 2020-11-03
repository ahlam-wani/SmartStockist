package com.smartstockist.app.data.model

import com.google.gson.annotations.SerializedName

data class GetClients(@SerializedName("id")
                        val id:String,
                     @SerializedName ("balance")
                        val balance:Float,
                     @SerializedName("code")
                       val code:String,
                     @SerializedName("name")
                      val name:String,
                     @SerializedName("area")
                      val area:String,
                     @SerializedName("address")
                      val address:String,
                     @SerializedName("mainContact")
                      val mainContact:String,
                     @SerializedName("mainContactNumber")
                      val mainContactNumber: String,
                     @SerializedName("openingBalanceDate")
                      val openingBalanceDate: String,
                     @SerializedName("openingBalance")
                      val openingBalance: Float,
                     @SerializedName("status")
                      val status: String,
                     @SerializedName("secondContact")
                      val secondContact:String,
                     @SerializedName("secondContactNumber")
                     val secondContactNumber:String,
                     @SerializedName("tinNumber")
                     val tinNumber:String,
                     @SerializedName("type")
                     val type: Int,
                     @SerializedName("loginId")
                     val loginId: Int,
                     @SerializedName("stateStatus")
                     val stateStatus:Int,
                     @SerializedName("faxNumber")
                     val faxNumber:String,
                     @SerializedName("clientSince")
                     val clientSince: String,
                     @SerializedName("email")
                     val email: String,
                     @SerializedName("agentId")
                     var agentId:String?,
                     @SerializedName("agentPercentage")
                     val agentPercentage:Long,
                     @SerializedName("priceText")
                     val priceText: Nothing? = null,
                     @SerializedName("reportFilter")
                     val reportFiter:Boolean,
                     @SerializedName("creditLimit")
                     val creditLimit: Long,
                     @SerializedName("autoPaid")
                     val autoPaid: Long,
                     @SerializedName("salesPersonId")
                     val salesPersonId:String,
                     @SerializedName("invoiceLimit")
                     val invoiceLimit: Int,
                     @SerializedName("isMember")
                     val isMember: Boolean,
                     @SerializedName("paymentTerm")
                     val paymentTerm: Int,
                     @SerializedName("warehouseId")
                     val warehouseId: String,
                     @SerializedName("state")
                     val state: Int,
                     @SerializedName("createdById")
                     val createdId:String?=null,
                     @SerializedName("lastUpdatedById")
                     val lastUpdatedId: String,
                     @SerializedName("createdOn")
                     val createdOn: String,
                     @SerializedName("lastUpdated")
                     val lastUpdated: String)