package io.skyhills.jaguarscala

import cats.effect._
import com.typesafe.config._
import doobie.util.transactor.Transactor

/**
  * Created by soeunpark on 2018. 6. 25..
  */
object Database {

    val conf: Config = ConfigFactory.load()

    def transactor(): Transactor[IO] = {
        Transactor.fromDriverManager(
            conf.getString("db.driver"),
            conf.getString("db.url"),
            conf.getString("db.user"),
            conf.getString("db.password")
        )
    }
}
