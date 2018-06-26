package io.skyhills.jaguarscala.repository

import cats.effect.IO
import doobie.implicits._
import fs2.Stream
import doobie.h2.H2Transactor
import io.skyhills.jaguarscala.Corporation

/**
  * Created by soeunpark on 2018. 6. 26..
  */
class TestRepository(xa: IO[H2Transactor[IO]]) {
    //FIXME this method doesn't have to return Stream.
    def getCorp(id: Long): Stream[IO, Corporation] = {
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE corpId = $id".query[Corporation].stream.transact(xa.unsafeRunSync())
    }

    def getCorps(isFavorite: Boolean): Stream[IO, Corporation] = {
        sql"SELECT corpId, corpName, isFavorite FROM Corporation WHERE isFavorite = $isFavorite".query[Corporation].stream.transact(xa.unsafeRunSync())
    }
}
