package aws

import org.slf4j.LoggerFactory

import scala.io.Source

object CheckSnapshot extends App{

  val log = LoggerFactory.getLogger(getClass)

  log.info("start to diff snapshot files")

  val mysnap = Source.fromFile("M:\\mylist.txt").getLines.toList.par

  mysnap.foreach { snapshotId =>
    val toDelete = Source.fromFile("M:\\delete.txt").getLines

    toDelete.foreach{ line =>
      if (snapshotId.trim == line.trim)
        log.info(snapshotId)
    }
  }
}
