package com.mpojeda84.mapr.scala

import java.security.MessageDigest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.ojai.joda.DateTime

object ConfigurationHelper {

  implicit class MapUtil(map: Map[String, String]) {

    val objectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

    def get(): Map[String, String] = map

    def addTimestamp() : Map[String, String] = map + ("timeStamp" ->  DateTime.now().toString)

    def addId() : Map[String, String] = map + ("_id" -> (MessageDigest.getInstance("MD5").digest(objectMapper.writeValueAsString(map).getBytes).toString + map.get("timeStamp")))

    def toJSON() = objectMapper.writeValueAsString(map)

  }

}
