package spec

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, ShouldMatchers}

import scala.concurrent.duration._

class UnitSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with ShouldMatchers
  with FlatSpecLike
  with BeforeAndAfterAll
{

  override def afterAll() = {
    system.shutdown()
    system.awaitTermination(10 seconds)
  }

}
