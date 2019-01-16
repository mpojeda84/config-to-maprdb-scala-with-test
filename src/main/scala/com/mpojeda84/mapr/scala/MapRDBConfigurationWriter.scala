package com.mpojeda84.mapr.scala

import org.apache.spark.sql.RuntimeConfig
import org.ojai.store.{DriverManager}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import ConfigurationHelper._

object MapRDBConfigurationWriter {

  def apply(conf: RuntimeConfig, tableName: String): ConfigurationWriter = new ConfigurationWriter {
    override def write(): Future[Unit] = Future {

      val connection = DriverManager.getConnection("ojai:mapr:")
      val store = connection.getStore(tableName)

      val jsonString =  conf.getAll.addTimestamp().addId().toJSON()
      store.insertOrReplace(connection.newDocument(jsonString))

      store.close()
      connection.close()
    }
  }



}