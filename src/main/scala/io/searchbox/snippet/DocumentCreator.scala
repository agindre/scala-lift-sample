package io.searchbox {
package snippet {

import xml.NodeSeq
import net.liftweb.http.S
import net.liftweb.common.Box
import client.JestClient
import lib.DependencyFactory
import indices.CreateIndex
import java.lang.String
import java.util
import core.Index


class DocumentCreator {

	val client: Box[JestClient] = DependencyFactory.inject[JestClient]

	val doc1 = new util.LinkedHashMap[String, String]
	doc1.put("message", "world of warcraft")

	val doc2 = new util.LinkedHashMap[String, String]
	doc1.put("message", "starcraft")


	def handle(in: NodeSeq): NodeSeq = {

		val jestClient = client.openTheBox

		val op = S.param("op") openOr ""

		op match {
			case "index" => createIndex(jestClient)
			case "doc1" => createDoc(jestClient, doc1)
			case "doc2" => createDoc(jestClient, doc2)
			case _ => <p>Unknown Operation</p>
		}
	}


	def createIndex(client: JestClient) = {
		client.execute(new CreateIndex("games"))
		<p>Created Index</p>
	}

	def createDoc(client: JestClient, doc: util.LinkedHashMap[String, String]) = {
		val index: Index = new Index.Builder(doc).index("games").`type`("game").build()
		client.execute(index)
		<p>Created +
			{doc}
		</p>
	}

}

}

}

