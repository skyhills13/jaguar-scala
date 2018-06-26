package io.skyhills.jaguarscala.service

import cats.effect.Effect
import fs2.Stream
import io.circe.{Encoder, Json}
import io.skyhills.jaguarscala.repository.TestRepository
import org.http4s.{HttpService, MediaType}
import org.http4s.circe._
import io.circe.syntax._
import io.skyhills.jaguarscala.Corporation
import org.http4s.dsl.Http4sDsl

class HelloWorldService[F[_] : Effect](repository: TestRepository) extends Http4sDsl[F] {

    val service: HttpService[F] = {
        HttpService[F] {
            case GET -> Root / "hello" / name =>
                Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

//            case GET -> Root / "test" / LongVar(id) =>

        }
    }
}
