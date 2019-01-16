package com.mpojeda84.mapr.scala

import scala.concurrent.Future

trait ConfigurationWriter {
  def write(): Future[Unit]
}