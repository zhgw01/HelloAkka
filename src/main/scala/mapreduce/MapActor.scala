package mapreduce

import akka.actor.Actor

import scala.collection.mutable.ArrayBuffer

class MapActor extends Actor{

  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as",
    "at", "be", "do", "go", "if", "in", "is", "it", "of", "on", "the",
    "to")
  val defaultCount: Int = 1

  def receive: Receive = {
    case message: String => sender ! evaluateExpression(message)
  }

  def evaluateExpression(line: String): MapData = MapData {
    line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
      (array, word) =>
        if(!STOP_WORDS_LIST.contains(word.toLowerCase))
          array += WordCount(word.toLowerCase, 1)
        else
          array
    }
  }

}
