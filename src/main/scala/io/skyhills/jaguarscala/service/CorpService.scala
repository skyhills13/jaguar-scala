package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import org.http4s.circe._
import io.skyhills.jaguarscala.Database
import io.skyhills.jaguarscala.repository.CorpRepository
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

import scala.io.Source

object CorpService extends Http4sDsl[IO] {

    val repository: CorpRepository = new CorpRepository(Database.transactor())
    val service: HttpService[IO] = HttpService[IO] {
        case GET -> Root / "init" =>
            val insertedCount = initializeCorps()
            Ok(Json.obj("count" -> Json.fromInt(insertedCount)))
    }

    /*
    NOTE corpId is always 6 digit numbers with leading 0s
    ex 카카오 035720
    */
    def initializeCorps(): Int =
        Source.fromURL(getClass.getResource("/corp.csv"))
            .getLines()
            .foldLeft(0) { (sum, line) =>
                repository.insertCorp(
                    line.split(",").head,
                    "%06d".format(line.split(",").last.toInt),
                    isFavorite = false
                )
                sum + 1
            }
}