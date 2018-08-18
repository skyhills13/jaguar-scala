package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

/**
  * Created by soeunpark on 2018. 5. 25..
  */
object TransactionService extends Http4sDsl[IO] {

    val service: HttpService[IO] = HttpService[IO] {
        case GET -> Root / IntVar(corpId) =>
            Ok(Json.obj("corpId" -> Json.fromInt(corpId), "corpName" -> Json.fromString("aaa")))

    }
}

