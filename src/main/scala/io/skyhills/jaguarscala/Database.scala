package io.skyhills.jaguarscala

import cats.effect._
import doobie.h2.H2Transactor

/**
  * Created by soeunpark on 2018. 6. 25..
  */
object Database {

    def transactor():IO[H2Transactor[IO]] = {
        H2Transactor.newH2Transactor[IO](
            "jdbc:h2:~/jaguar",
            "sa",
            ""
        )
    }
}
