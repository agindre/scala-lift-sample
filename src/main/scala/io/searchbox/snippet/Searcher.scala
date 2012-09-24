package io.searchbox {
package snippet {

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.http.S
import client.config.{ClientConstants, ClientConfig}
import java.util
import core.Search
import client.{JestResult, JestClient, JestClientFactory}
import org.elasticsearch.index.query.{QueryBuilders, QueryBuilder}

class Searcher {
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

	def results(in: NodeSeq): NodeSeq = {

		val q = S.param("q") openOr ""

		val queryBuilder: QueryBuilder = QueryBuilders.queryString(q)

		val search: Search = new Search(queryBuilder)
		search.addIndex("games")
		search.addType("wordpress")

		val result: JestResult = client.execute(search)

		<p>
			{result.getJsonMap}
		</p>
	}
}

}

}
