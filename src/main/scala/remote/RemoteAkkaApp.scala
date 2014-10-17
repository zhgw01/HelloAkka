package remote

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

object RemoteAkkaApp extends App{

  val config = ConfigFactory.load()
  val log = LoggerFactory.getLogger(getClass)
  //val backend = ActorSystem("backend-actorsystem")

  log.info("start application {}", config.getString("name"))
}
