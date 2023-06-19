package org.sk.spscala.spark

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.sql.SQLContext
import org.sk.spscala.spark.mask.MaskFunctions

package object sql {
  /**
    * :: DeveloperApi ::
    *
    * to implicitly register masking algorithm on SQLContext
    *
    * @param sqlContext the SQLContext
    * @return the Azure SQL based SQLContext
    */
  @DeveloperApi
  implicit def toRegisterMAFunctions(sqlContext: SQLContext): RegisterMAFunctions = RegisterMAFunctions(sqlContext)

  /**
    * :: DeveloperApi ::
    *
    * to implicitly unRegister masking algorithm from SQLContext
    *
    * @param sqlContext the SQLContext
    * @return the Azure SQL based SQLContext
    */
  @DeveloperApi
  implicit def toUnRegisterMAFunctions(sqlContext: SQLContext): UnRegisterMAFunctions = UnRegisterMAFunctions(sqlContext)

}
