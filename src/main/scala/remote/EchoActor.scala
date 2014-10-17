package remote

import akka.actor.{ActorLogging, Actor}

class EchoActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case m: String =>
      log.info("Receive message {}", m)
      sender ! m
  }
}
