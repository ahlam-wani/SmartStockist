package com.smartstockist.app.di

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.smartstockist.app.data.model.GetClients
import com.smartstockist.app.data.remote.NetworkState


class ClientDataSourceFactory:DataSource.Factory<Int,GetClients> (){

    val clientLiveDataSource=MutableLiveData<ClientDataSource>()
    override fun create(): DataSource<Int, GetClients> {
        val clientDataSource=ClientDataSource()
        clientLiveDataSource.postValue(clientDataSource)
        return clientDataSource

    }
}