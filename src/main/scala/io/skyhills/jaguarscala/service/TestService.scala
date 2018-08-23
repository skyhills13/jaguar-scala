package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import io.skyhills.jaguarscala.repository.TestRepository
import io.skyhills.jaguarscala.{Corporation, Database}
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

object TestService extends Http4sDsl[IO] {

    val repository: TestRepository = new TestRepository(Database.transactor())

    val service: HttpService[IO] = HttpService[IO] {
        case GET -> Root / "hello" / name =>
            Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

        case GET -> Root / "test" =>
            Ok(Corporation(1, "aaa", true).asJson)

        case GET -> Root / "test" / LongVar(corpId) =>
            val corp = repository.getCorp(corpId).map(_.asJson)
            corp match {
                case Some(c) =>
                    Ok(c)
                case None =>
                    NotFound("Corporation Not Found")
            }

        case GET -> Root / "fav" =>
            val corps = repository.getCorps(true, 5).map(_.asJson)
            Ok(Json.fromValues(corps))
    }
}
