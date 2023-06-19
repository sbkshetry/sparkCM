package org.sk.spscala.spark.sqlContext

import org.apache.spark.sql.{SQLContext, SparkSession}

object SqlContextProvider {

  private var sqlContext:SQLContext=_

  def provide():SQLContext={
    if(sqlContext==null)
    synchronized {
      val sparkSession = SparkSession.builder()
        .master("local[*]")
        .getOrCreate()
      sqlContext=sparkSession.sqlContext
    }
    sqlContext
  }
}
