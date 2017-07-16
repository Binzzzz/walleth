package org.walleth.data.networks

import org.walleth.data.SimpleObserveable

class NetworkDefinitionProvider : SimpleObserveable(){
    val allDefinitions = mutableListOf(RinkebyNetworkDefinition(), MainnetNetworkDefinition())

    var currentDefinition: NetworkDefinition = RinkebyNetworkDefinition()
        set(value) {
            field = value
            promoteChange()
        }
}