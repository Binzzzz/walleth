package org.walleth.data.networks

import android.arch.lifecycle.MutableLiveData

class NetworkDefinitionProvider : MutableLiveData<NetworkDefinition>() {
    val allDefinitions = mutableListOf(RinkebyNetworkDefinition(), MainnetNetworkDefinition(), RopstenNetworkDefinition())

    init {
        value = allDefinitions.first()
    }

    fun setCurrent(value: NetworkDefinition) {
        setValue(value)
    }
}