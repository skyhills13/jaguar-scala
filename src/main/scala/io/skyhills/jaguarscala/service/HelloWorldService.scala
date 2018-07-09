package io.skyhills.jaguarscala.service

import cats.effect.Effect
import io.circe.{Encoder, Json}
import io.skyhills.jaguarscala.repository.TestRepository
import org.http4s.HttpService
import org.http4s.circe._
import io.skyhills.jaguarscala.Corporation
import org.http4s.dsl.Http4sDsl
import io.circe.generic.auto._
import io.circe.syntax._

class HelloWorldService[F[_] : Effect](repository: TestRepository) extends Http4sDsl[F] {

    val service: HttpService[F] = {
        HttpService[F] {
            case GET -> Root / "hello" / name =>
                Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

            case GET -> Root / "test" =>
                Ok(Corporation(1, "aaa", true).asJson)

//            case GET -> Root / "test" /  LongVar(corpId) =>
//                Ok(repository.getCorp(corpId).map(_.asJson))
        }
    }
}
