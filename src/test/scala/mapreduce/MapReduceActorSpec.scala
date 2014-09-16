package mapreduce

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import spec.UnitSpec

import scala.collection.mutable.ArrayBuffer

class MapReduceActorSpec(_system: ActorSystem) extends UnitSpec(_system){

  def this() = this(ActorSystem("MapReduceActorSpec"))

  "A MapActor" should "return (hello, 1) on receive message 'hello'" in {

    val mapper = TestActorRef[MapActor]
    mapper ! "hello"
    expectMsg(MapData(ArrayBuffer(WordCount("hello", 1))))
  }

  "A ReduceActor" should "return ('hell0'->1) on receive message ('hello', 1)" in {

    val reducer = TestActorRef[ReduceActor]
    val mapData = MapData(ArrayBuffer(WordCount("hello", 1)))

    reducer ! mapData
    expectMsg(ReduceData(Map[String, Int]("hello"->1)))
  }

}
