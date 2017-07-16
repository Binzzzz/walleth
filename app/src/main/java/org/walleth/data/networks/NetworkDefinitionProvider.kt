package org.walleth.data.networks

class NetworkDefinitionProvider {
    val allDefinitions = mutableListOf( RinkebyNetworkDefinition() , MainnetNetworkDefinition())
    var currentDefinition: NetworkDefinition = RinkebyNetworkDefinition()
}