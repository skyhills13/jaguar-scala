package io.skyhills.jaguarscala.service

import cats.effect.Effect
import org.http4s.dsl.Http4sDsl

/**
  * Created by soeunpark on 2018. 5. 25..
  */
class WishService[F[_] : Effect] extends Http4sDsl[F] {

}
