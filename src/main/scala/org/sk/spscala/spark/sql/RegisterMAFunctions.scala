package org.sk.spscala.spark.sql

import com.google.gson.Gson
import org.apache.spark.sql.SQLContext
import org.sk.spscala.spark.LoggingTrait
import org.sk.spscala.spark.utils.AlgorithmNameHolder
import org.sk.spscala.spark.utils.Constants._

/**
 * to register masking algorithm
 * @param sqlContext
 */
private[spark] case class RegisterMAFunctions(@transient sqlContext: SQLContext) extends LoggingTrait {
  /**
   * to register masking algorithm
   * @param maskingAlgorithmName
   * @param leftKey
   * @param rightKey
   * @param key
   * @return SQLContext
   */

  def registerMA(maskingAlgorithmName:String = DEFAULT_MASKING_ALGORITHM,leftKey:String = DEFAULT_LEFT_KEY,rightKey:String = DEFAULT_RIGHT_KEY,key:String=Thread.currentThread().getName): SQLContext = {
    AlgorithmNameHolder.register(maskingAlgorithmName,leftKey ,rightKey,key)
    val aDetails=AlgorithmNameHolder.getName(key)
    sqlContext.setConf(MASKING_ALGORITHM_KEY,new Gson().toJson(aDetails))
    return sqlContext
  }

}
