package mapreduce

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import akka.actor.{Props, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout

object MapReduceApplication extends App{

  val _system = ActorSystem("MapReduceApplication")
  val master = _system.actorOf(Props[MasterActor], "master")
  implicit val timeout = Timeout(5 seconds)

  master ! "The quick brown fox tried to jump over the lazy dog and fell on the dog"
  master ! "Dog is man's best friend"
  master ! "Dog and Fox belong to the same family"

  Thread.sleep(500)
  val future = (master ? Result).mapTo[String]
  val result = Await.result(future, timeout.duration)
  println(result)
  _system.shutdown()

}
