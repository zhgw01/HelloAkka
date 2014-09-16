package mapreduce

import akka.actor.Actor

import scala.collection.mutable

class AggregateActor extends Actor{

  val finalReduceMap = new mutable.HashMap[String, Int]

  def receive: Receive = {
    case ReduceData(reduceDataMap) =>
      aggregateInMemoryReduce(reduceDataMap)

    case Result =>
      sender ! finalReduceMap.toString()
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Int]): Unit = {
    for( (k, v) <- reducedList) {
      finalReduceMap += (k -> (finalReduceMap.getOrElse(k, 0) + v))
    }
  }

}
