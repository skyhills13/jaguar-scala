package io.skyhills.jaguarscala

import cats.effect._
import com.typesafe.config._
import doobie.h2.H2Transactor

/**
  * Created by soeunpark on 2018. 6. 25..
  */
object Database {

    val conf: Config = ConfigFactory.load()

    def transactor(): H2Transactor[IO] = {
        H2Transactor.newH2Transactor[IO](
            conf.getString("db.url"),
            conf.getString("db.user"),
            conf.getString("db.password")
        ).unsafeRunSync()
    }
}
