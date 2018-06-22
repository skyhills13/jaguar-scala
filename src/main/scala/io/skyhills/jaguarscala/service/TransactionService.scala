package io.skyhills.jaguarscala.service

import cats.effect.{Effect, IO}
import io.circe.Json
import org.http4s.circe._
import org.http4s.{EntityEncoder, HttpService}
import org.http4s.dsl.Http4sDsl
import com.github.nscala_time.time.Imports._

/**
  * Created by soeunpark on 2018. 5. 25..
  */
class TransactionService[F[_] : Effect] extends Http4sDsl[F] {

    val service: HttpService[F] = {
        HttpService[F] {
            case GET -> Root / IntVar(corpId) =>
                Ok(Json.obj("corpId" -> Json.fromInt(corpId), "corpName" -> Json.fromString("aaa")))

            case GET -> Root / IntVar(corpId) / "sell" =>
                Ok()

            case GET -> Root / IntVar(corpId) / "buy" =>
                Ok()

            case POST -> Root / IntVar(corpId) / "sell" =>
                Ok()

            case POST -> Root / IntVar(corpId) / "buy" =>
                Ok()
        }
    }
}

