package com.mpojeda84.mapr.scala

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.scalatest._

class ConfigurationHelperTest extends FlatSpec with Matchers {

  it should "add timestamp key to map" in {
    val map = ConfigurationHelper.MapUtil(Map("a" -> "b")).addTimestamp()
    map.get("timeStamp") should not be null
    //println(map.get("timeStamp"))
  }

  it should "add _id key to map" in {
    val map = ConfigurationHelper.MapUtil(Map("a" -> "b")).addId()
    map.get("_id") should not be null
    //println(map.get("_id"))
  }

  it should "serialize map to JSON" in {
    val json = ConfigurationHelper.MapUtil(Map("a" -> "b")).toJSON()
    val mapper = new ObjectMapper().registerModule(DefaultScalaModule)

    val tree = mapper.readTree(json)
    tree.get("a").asText() should be equals("b")
  }

}