import com.typesafe.config.ConfigFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object HelloAkkaScala extends App {

  val logger = LoggerFactory.getLogger(getClass)


  val conf = ConfigFactory.load()
  logger.debug("conf {}", conf.getString("hello"))
}
