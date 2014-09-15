package mapreduce

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import spec.UnitSpec

import scala.collection.mutable.ArrayBuffer

class MapReduceActorSpec(_system: ActorSystem) extends UnitSpec(_system){

  def this() = this(ActorSystem("MapReduceActorSpec"))

  "A MapActor" should "return (hello, 1) on receive messge 'hello'" in {

    val mapper = TestActorRef[MapActor]
    mapper ! "hello"
    expectMsg(MapData(ArrayBuffer(WordCount("hello", 1))))
  }

}
