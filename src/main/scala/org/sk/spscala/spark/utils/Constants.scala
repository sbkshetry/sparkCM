package org.sk.spscala.spark.utils

import scala.util.Random

/**
 *
 */
private[spark] object Constants {
  val EMPTY=""
  val SPACE=" "
  val COMMA=","
  val UNDER_SCORE="_"
  val SEMI_COLON=";"
  val HASH="#"
  val DOT="."
  val AT_SIGN="@"
  val ZERO:Int=0
  val ZERO_DOUBLE:Double=0.0
  val ONE:Int=1
  val TWO:Int=2
  val THREE:Int=3
  val FOUR:Int=4
  val FIVE:Int=5
  val DEFAULT_MASKING_ALGORITHM="sha2"
  val MASKING_ALGORITHM_KEY=AT_SIGN+"SparkCM_AK"
  val SUPPORTED_MASKING_ALGORITHM=Set("sha1","sha2","md5")
  val DEFAULT_LEFT_KEY=AT_SIGN+"LK"+new Random(1000).nextInt()
  val DEFAULT_RIGHT_KEY=AT_SIGN+"RK"+new Random(1000).nextInt()
  val VW_UNDER_SCORE="vw"+UNDER_SCORE
  val MINUS="-"
}
