package mapreduce

import akka.actor.{Props, Actor}
import akka.routing.RoundRobinPool

class MasterActor extends Actor{

  val mapActor = context.actorOf(Props[MapActor].withRouter(new RoundRobinPool(5)), "map")
  val reduceActor = context.actorOf(Props[ReduceActor].withRouter(new RoundRobinPool(5)), "reduce")
  //aggregate actor has its own state: finalmap
  val aggregateActor = context.actorOf(Props[AggregateActor])

  def receive: Receive = {
    case line: String => mapActor ! line
    case mapData: MapData => reduceActor ! mapData
    case reduceData: ReduceData => aggregateActor ! reduceData
    case Result => aggregateActor forward Result
  }
}
