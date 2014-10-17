import org.scalatest.{ BeforeAndAfterAll, FlatSpecLike, Matchers }
import akka.actor.{ Actor, Props, ActorSystem }
import akka.testkit.{ ImplicitSender, TestKit, TestActorRef }
import spec.UnitSpec
import scala.concurrent.duration._

class HelloAkkaSpec(_system: ActorSystem)
  extends UnitSpec(_system) {

  def this() = this(ActorSystem("HelloAkkaSpec"))

  override def afterAll() = {
    system.shutdown()
    system.awaitTermination(10.seconds)
  }

  "An HelloAkkaActor" should "be able to set a new greeting" in {
    val greeter = TestActorRef(Props[Greeter])
    greeter ! WhoToGreet("testkit")
    greeter.underlyingActor.asInstanceOf[Greeter].greeting should be("hello, testkit")
  }

  it should "be able to get a new greeting" in {
    val greeter = system.actorOf(Props[Greeter], "greeter")
    greeter ! WhoToGreet("dan")
    greeter ! Greet
    expectMsg(Greeting("hello, dan"))
  }
}
