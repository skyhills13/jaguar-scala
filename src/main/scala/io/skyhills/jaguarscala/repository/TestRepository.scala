package io.skyhills.jaguarscala.repository

import cats.effect.IO
import doobie.implicits._
import fs2.Stream
import doobie.h2.H2Transactor
import io.skyhills.jaguarscala.Corporation

/**
  * Created by soeunpark on 2018. 6. 26..
  */
class TestRepository(xa: H2Transactor[IO]) {
    def getCorp(id: Long): Option[Corporation] = {
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE corpId = $id"
         .query[Corporation].stream.take(1).compile.toList.map(_.headOption).transact(xa).unsafeRunSync()
    }

    def getCorps(isFavorite: Boolean, count: Int): List[Corporation] = {
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE isFavorite = $isFavorite"
            .query[Corporation].stream.take(count).compile.toList.transact(xa).unsafeRunSync()
    }
}
