package io.searchbox {
package snippet {

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.http.S
import core.Search
import client.{JestResult, JestClient}
import org.elasticsearch.index.query.{QueryBuilders, QueryBuilder}
import net.liftweb.common.Box
import lib.DependencyFactory
import net.liftweb.json._
import java.util

class Searcher {
	val client: Box[JestClient] = DependencyFactory.inject[JestClient]

	def results(in: NodeSeq): NodeSeq = {


		val jestClient=client.openTheBox

		val q = S.param("q") openOr ""

		val queryBuilder: QueryBuilder = QueryBuilders.queryString(q)

		val search: Search = new Search(queryBuilder)
		search.addIndex("games")
		search.addType("game")

		val result: JestResult = jestClient.execute(search)

		val json = parse(result.getJsonString)

			{ for {  JObject(child) <- json
               JField("name", JString(name)) <- child
              JField("description", JString(description)) <- child} yield <tr><td>{name}</td><td>{description}</td></tr> }
	}
}

}

}
