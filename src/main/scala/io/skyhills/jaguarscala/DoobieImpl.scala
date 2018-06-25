package io.skyhills.jaguarscala

import doobie._
import doobie.implicits._

import cats._
import cats.effect._
import cats.implicits._

/**
  * Created by soeunpark on 2018. 6. 25..
  */
class DoobieImpl {
    val program1 = 42.pure[ConnectionIO]
}
