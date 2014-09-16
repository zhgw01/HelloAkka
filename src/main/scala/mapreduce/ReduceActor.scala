package mapreduce

import akka.actor.Actor

class ReduceActor extends Actor{

  def receive: Receive = {
    case MapData(dataList) => sender ! reduce(dataList)
  }

  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    words.foldLeft(Map.empty[String, Int]) {
      (maps, wordCount) =>
          val word = wordCount.word
          maps + (word -> (maps.getOrElse(word, 0) + 1))
    }
  }
}
