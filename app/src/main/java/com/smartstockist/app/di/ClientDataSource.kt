package com.smartstockist.app.di
import androidx.paging.PageKeyedDataSource
import com.smartstockist.app.data.model.GetClients
import com.smartstockist.app.data.remote.Api
import com.smartstockist.app.data.remote.ApiService
import com.smartstockist.app.data.remote.NetworkState
import retrofit2.Call
import retrofit2.Response

class ClientDataSource :PageKeyedDataSource<Int,GetClients>(){
    companion object{
        const val FIRST_PAGE=1
        const val PAGE_SIZE=25
    }
   private val apiService by lazy{
        Api.retrofit.create(ApiService::class.java)
    }
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetClients>
    ) {
        val call=apiService.Clients("Number $FIRST_PAGE")
        call.enqueue(object :retrofit2.Callback<List<GetClients>>{
            override fun onResponse(
                call: Call<List<GetClients>>,
                response: Response<List<GetClients>>
            ) {
                if (response.code()==200){
                    val apiResponse=response.body()
                   val respos= NetworkState.Success(apiResponse)
                    println("bodey = ${response.body()}")
                    apiResponse?.let {
                        callback.onResult(apiResponse,null, FIRST_PAGE+1)
                    }
                }

            }

            override fun onFailure(call: Call<List<GetClients>>, t: Throwable) {
               //NetworkState.Failure(t.message.toString())
                println("error ${t.message}")

            }

        })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GetClients>) {
        val call=apiService.Clients("Number ${params.key}")
        call.enqueue(object :retrofit2.Callback<List<GetClients>>{
            override fun onResponse(
                call: Call<List<GetClients>>,
                response: Response<List<GetClients>>
            ) {
                if (response.code() == 200){
                    val apiResponse=response.body()!!
                    val key=if (params.key>1) params.key-1 else 0
                    apiResponse?.let {
                        callback.onResult(apiResponse,key)
                    }
                }
            }

            override fun onFailure(call: Call<List<GetClients>>, t: Throwable) {
            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GetClients>) {
           val  call=apiService.Clients("Number ${params.key}")
        call.enqueue(object :retrofit2.Callback<List<GetClients>>{
            override fun onResponse(
                call: Call<List<GetClients>>,
                response: Response<List<GetClients>>
            ) {
                val apiResponse=response.body()!!
                val key=params.key+1
                apiResponse?.let {
                    callback.onResult(apiResponse,key)
                }
            }

            override fun onFailure(call: Call<List<GetClients>>, t: Throwable) {

            }

        })

    }
}