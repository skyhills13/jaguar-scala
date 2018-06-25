package io.skyhills.jaguarscala

import doobie._
import cats.effect._
import doobie.h2.H2Transactor

/**
  * Created by soeunpark on 2018. 6. 25..
  */
object Database {

    def transactor():IO[Transactor[IO]] = {
        H2Transactor.newH2Transactor[IO](
            "jdbc:h2:tcp://localhost/~/test",
            "sa",
            ""
        )
    }

    def init(transactor: Transactor[IO]): IO[Unit] = ???
}
