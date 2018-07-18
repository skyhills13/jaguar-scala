package io.skyhills.jaguarscala

import cats.effect.IO
import doobie._

/**
  * Created by soeunpark on 2018. 6. 25..
  */
object Database {
    def transactor(): Transactor[IO] = {
        Transactor.fromDriverManager(
            "org.postgresql.Driver",
            "jdbc:postgresql:jaguar",
            "soeunpark",
            ""
        )
    }
}