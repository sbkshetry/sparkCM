package org.sk.spscala.spark

import scala.language.implicitConversions
import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.sql._

package object mask {


  /**
    * :: DeveloperApi ::
    *
    * to implicitly add mask functions to a DataFrame
    *
    * @param sqlContext the DataFrame
    * @return the Mask based DataFrame
    */
  @DeveloperApi
  implicit def toMaskFunctions(dataFrame: DataFrame): MaskFunctions = MaskFunctions(dataFrame)
}
