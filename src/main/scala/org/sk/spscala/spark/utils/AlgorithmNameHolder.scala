package org.sk.spscala.spark.utils

import java.util
import java.util.concurrent.atomic.AtomicInteger

/**
 * to register and unregister masking algorithm
 */
private[spark] object AlgorithmNameHolder {
  private val  counter=new AtomicInteger(0)
  private val  mapAK=new util.Hashtable[String,Int]()
  private val  mapAN=new util.Hashtable[String,(String,String,String)]()
  private val defaultPrefix=Constants.MASKING_ALGORITHM_KEY
  def  register(aName:String,leftKey:String,rightKey:String,key:String=Thread.currentThread().getName):Unit={
    if(aName==null){
      throw new IllegalArgumentException("Algorithm name is missing.")
    }
    if(!Constants.SUPPORTED_MASKING_ALGORITHM.contains(aName.toLowerCase)){
      throw new IllegalArgumentException(s"$aName is not Supported Algorithm. Current Supported Algorithms are ${Constants.SUPPORTED_MASKING_ALGORITHM.mkString(",")}")
    }
    if (!mapAK.containsKey(key)){
      val aKey=counter.getAndIncrement()
      mapAK.put(key,aKey)
      mapAN.put(defaultPrefix+mapAK.get(key),(Option(leftKey).getOrElse(Constants.DEFAULT_LEFT_KEY) ,aName,Option(rightKey).getOrElse(Constants.DEFAULT_RIGHT_KEY)))
    }
  }

  def getName(key:String=Thread.currentThread().getName):(String,String,String)={
    if (!mapAK.containsKey(key)){
       register(Constants.DEFAULT_MASKING_ALGORITHM,leftKey = Constants.DEFAULT_LEFT_KEY,rightKey = Constants.DEFAULT_RIGHT_KEY,key)
    }
    mapAN.get(defaultPrefix+mapAK.get(key))
  }

  def unRegister(aName:String,key:String=Thread.currentThread().getName):Unit= {
    if (mapAK.containsKey(key)) {
      counter.getAndDecrement()
      mapAN.remove(defaultPrefix+mapAK.get(key))
      mapAK.remove(key)
    }
  }
}
