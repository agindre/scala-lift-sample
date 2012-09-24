package io.searchbox {
package lib {

import net.liftweb._
import util._
import client.config.{ClientConstants, ClientConfig}
import java.util
import client.{JestClient, JestClientFactory}


object DependencyFactory extends SimpleInjector {

	// register the function that builds JestClient
	DependencyFactory.registerInjection(buildOne _)

	def buildOne(): JestClient = {
		// Configuration
		val clientConfig: ClientConfig = new ClientConfig()
		val servers: util.LinkedHashSet[String] = new util.LinkedHashSet[String]()
		servers.add("https://api.searchbox.io/api-key/51be11df9f81d78292d9d71dbb373056")
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers)
		clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED,java.lang.Boolean.TRUE)

		// Construct a new Jest client according to configuration via factory
		val factory: JestClientFactory = new JestClientFactory()
		factory.setClientConfig(clientConfig)
		val client: JestClient = factory.getObject()
		client
	}



}

}

}