package io.skyhills.jaguarscala.repository

import cats.effect.IO
import doobie.implicits._
import doobie.util.transactor.Transactor
import io.skyhills.jaguarscala.Corporation
import org.http4s.Request

/**
  * Created by soeunpark on 2018. 6. 26..
  */
class CorpRepository(xa: Transactor[IO]) {
    def getCorp(id: String): Option[Corporation] = {
        sql"SELECT corpId, corpName, isFavorite, order FROM Corporation WHERE corpId = $id"
          .query[Corporation].stream.take(1).compile.toList.map(_.headOption).transact(xa).unsafeRunSync()
    }

    def getCorps(isFavorite: Boolean, count: Int): List[Corporation] = {
        sql"SELECT corpId, corpName, isFavorite, order FROM Corporation WHERE isFavorite = $isFavorite ORDER BY order ASC"
          .query[Corporation].stream.take(count).compile.toList.transact(xa).unsafeRunSync()
    }

    def insertCorp(corpId: String, corpName: String, isFavorite: Boolean): Int = {
        sql"INSERT INTO Corporation (corpId, corpName, isFavorite) VALUES ($corpId, $corpName, $isFavorite)"
          .update.run.transact(xa).unsafeRunSync()
    }

    def insertCorp(corporation: Corporation): Int = {
        sql"INSERT INTO Corporation (corpId, corpName, isFavorite) VALUES (${corporation.corpId}, ${corporation.corpName}, ${corporation.isFavorite})"
          .update.run.transact(xa).unsafeRunSync()
    }

    //    def initCorps(corps: List[Corporation]): Int = {
    //        val sql = "INSERT INTO Corporation (corpId, corpName, isFavorite) values (?, ?, ?)"
    //        Update[Corporation](sql).updateMany(corps).transact(xa).unsafeRunSync()
    //    }

    def updateCorp(corpId: String, isFavorite: Boolean): Int = {
        sql"UPDATE Corporation SET isFavorite = $isFavorite WHERE corpId = $corpId"
          .update.run.transact(xa).unsafeRunSync()
    }

    def deleteCorp(corpId: String): Int = {
        sql"DELETE FROM Corporation WHERE corpId = $corpId"
          .update.run.transact(xa).unsafeRunSync()
    }
}
