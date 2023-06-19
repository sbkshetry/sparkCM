package org.sk.spscala.spark.sql

import org.apache.spark.sql.SQLContext
import org.sk.spscala.spark.LoggingTrait
import org.sk.spscala.spark.utils.AlgorithmNameHolder
import org.sk.spscala.spark.utils.Constants._

/**
 * to remove the existing registered masking algorithm
 * @param sqlContext
 */
private[spark] case class UnRegisterMAFunctions(@transient sqlContext: SQLContext) extends LoggingTrait {

  def unRegisterMA(key:String=Thread.currentThread().getName): SQLContext = {

    AlgorithmNameHolder.unRegister(key)
    sqlContext.setConf(MASKING_ALGORITHM_KEY,EMPTY)
    return sqlContext
  }

}
