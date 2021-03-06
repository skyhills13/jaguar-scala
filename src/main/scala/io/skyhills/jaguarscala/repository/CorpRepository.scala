package io.skyhills.jaguarscala.repository

import cats.effect.IO
import doobie.implicits._
import doobie.util.transactor.Transactor
import io.skyhills.jaguarscala.Corporation

class CorpRepository(xa: Transactor[IO]) {
    def getCorp(id: String): Option[Corporation] =
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE corpId = $id"
            .query[Corporation].stream.take(1).compile.toList.map(_.headOption).transact(xa).unsafeRunSync()

    def getCorps(isFavorite: Boolean, count: Int): List[Corporation] =
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE isFavorite = $isFavorite"
            .query[Corporation].stream.take(count).compile.toList.transact(xa).unsafeRunSync()

    def insertCorp(corpId: String, corpName: String, isFavorite: Boolean): Unit =
        sql"INSERT INTO Corporation (corpId, corpName, isFavorite) VALUES ($corpId, $corpName, $isFavorite)"
            .update.run.transact(xa).unsafeRunSync()
}