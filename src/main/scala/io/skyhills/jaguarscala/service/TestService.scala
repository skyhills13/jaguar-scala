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

class TestService[F[_] : Effect](repository: TestRepository) extends Http4sDsl[F] {

    val service: HttpService[F] = {
        HttpService[F] {
            case GET -> Root / "hello" / name =>
                Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

            case GET -> Root / "test" =>
                Ok(Corporation(1, "aaa", true).asJson)

            case GET -> Root / "test" / LongVar(corpId) =>
                /*
                repository.getCorp: Option[Corporation]
                map(Corporation => Json): Option[Json]
                */
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
}
