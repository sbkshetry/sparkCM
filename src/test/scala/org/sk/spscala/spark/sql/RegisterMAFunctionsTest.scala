package org.sk.spscala.spark.sql

import org.scalatest.funsuite.AnyFunSuite
import org.sk.spscala.spark.sqlContext.SqlContextProvider
import org.sk.spscala.spark.utils.Constants


class RegisterMAFunctionsTest  extends AnyFunSuite {


   test("testRegisterMA")  {
    val sqlContext: org.apache.spark.sql.SQLContext = SqlContextProvider.provide()
    val registerMAFunctions: org.sk.spscala.spark.sql.RegisterMAFunctions = new org.sk.spscala.spark.sql.RegisterMAFunctions(sqlContext)
     assert(sqlContext.getConf(Constants.MASKING_ALGORITHM_KEY).isEmpty)
     val res0: org.apache.spark.sql.SQLContext = registerMAFunctions.registerMA()
     assert(sqlContext.getConf(Constants.MASKING_ALGORITHM_KEY).nonEmpty)
     assert(res0.getConf(Constants.MASKING_ALGORITHM_KEY).nonEmpty)
    //val res1: org.apache.spark.sql.SQLContext = registerMAFunctions.registerMA("DEFAULT_MASKING_ALGORITHM")
    //val res2: org.apache.spark.sql.SQLContext = registerMAFunctions.registerMA("DEFAULT_MASKING_ALGORITHM", "DEFAULT_LEFT_KEY")
    //val res3: org.apache.spark.sql.SQLContext = registerMAFunctions.registerMA("DEFAULT_MASKING_ALGORITHM", "DEFAULT_LEFT_KEY", "DEFAULT_RIGHT_KEY")
    //val res4: org.apache.spark.sql.SQLContext = registerMAFunctions.registerMA("DEFAULT_MASKING_ALGORITHM", "DEFAULT_LEFT_KEY", "DEFAULT_RIGHT_KEY", "Thread.currentThread().getName")
  }


}
