package org.sk.spscala.spark.mask



import org.apache.spark.sql.DataFrame
import org.sk.spscala.spark.LoggingTrait
import org.sk.spscala.spark.utils.AlgorithmNameHolder
import org.sk.spscala.spark.utils.Constants._


private[spark] case class MaskFunctions(@transient dataFrame: DataFrame) extends LoggingTrait   {

  /**
   * to mask column data
   * @param aDetails
   * @param name
   * @return
   */

  private def getMaskColumn(aDetails: (String, String, String), name: String) : String={
    return s"cast( ${aDetails._2}(concat('${aDetails._1}','|',cast( $name as string),'|','${aDetails._3}') ${if(aDetails._2.equals("sha2"))",256" else ""}) as string) $name"
  }

  def mask(columns: Seq[String]): DataFrame = {
    try{
      if(columns.isEmpty){
        logError("masking column is not provided.")
        return dataFrame
      }
      val maskingColumnSet=columns.map(c=>c.toLowerCase).toSet
      val dfColumns=dataFrame.schema.fields.filter(
        f=>maskingColumnSet.contains(f.name.toLowerCase)).map(
        f=>(f.name.toLowerCase))
      if(dfColumns.isEmpty){
        logError(s"all masking columns [${columns.mkString(",")}] are not available in dataFrame.")
         dataFrame
      }
      else{
        val missingColumns=maskingColumnSet.--(dfColumns)
        if(missingColumns.nonEmpty)
          logWarning(s"some of masking columns [${missingColumns.mkString(",")}] are not available in dataFrame.")
        val tempTableName=VW_UNDER_SCORE+java.util.UUID.randomUUID.toString.replace(MINUS,UNDER_SCORE)
        dataFrame.createOrReplaceTempView(tempTableName)
        val aDetails= AlgorithmNameHolder.getName()
        val columns=dataFrame.schema.fields.map(s=>if(dfColumns.contains(s.name.toLowerCase))s"${getMaskColumn(aDetails,s.name)}"  else s.name)
        dataFrame.sqlContext.sql(s"select ${columns.mkString(",")} from $tempTableName")
      }
    }
    catch {
      case exception: Exception => {
        exception.printStackTrace()
        logError(exception.getMessage,exception)
        dataFrame
      }
    }
  }
}
