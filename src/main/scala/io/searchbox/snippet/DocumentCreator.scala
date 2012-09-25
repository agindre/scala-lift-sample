package io.searchbox {
package snippet {

import xml.NodeSeq
import net.liftweb.common.Box
import client.JestClient
import lib.DependencyFactory
import indices.{DeleteIndex, CreateIndex}
import java.lang.String
import java.util
import core.{Bulk, Index}


class DocumentCreator {

  val client: Box[JestClient] = DependencyFactory.inject[JestClient]

  val doc1 = new util.LinkedHashMap[String, String]
  doc1.put("name", "World of Warcraft")
  doc1.put("description", "World of Warcraft (often abbreviated as WoW) is a massively multiplayer online role-playing game (MMORPG) by Blizzard Entertainment")

  val doc2 = new util.LinkedHashMap[String, String]
  doc2.put("name", "StarCraft")
  doc2.put("description", "StarCraft is a military science fiction real-time strategy video game developed and published by Blizzard Entertainment")


  def handle(in: NodeSeq): NodeSeq = {

    val jestClient = client.openTheBox

    deleteIndex(jestClient)
    createIndex(jestClient)
    createDocsWithBulk(jestClient, util.Arrays.asList(doc1, doc2))
    <div class="alert">
      Two articles are created!
    </div>
  }

  def deleteIndex(client:JestClient) ={
    client.execute(new DeleteIndex("games"))
  }

  def createIndex(client: JestClient) = {
    client.execute(new CreateIndex("games"))
  }

  //Create with Index API
  def createDoc(client: JestClient, doc: util.LinkedHashMap[String, String]) = {
    val index: Index = new Index.Builder(doc).index("games").`type`("game").build()
    client.execute(index)
  }

  //Create with Bulk API
  def createDocsWithBulk(client: JestClient, docs:util.List[util.LinkedHashMap[String,String]]) = {

    val bulk: Bulk = new Bulk("games","game")
    bulk.addIndex(new Index.Builder(docs.get(0)).build())
    bulk.addIndex(new Index.Builder(docs.get(1)).build())

    client.execute(bulk)
  }

}

}

}

