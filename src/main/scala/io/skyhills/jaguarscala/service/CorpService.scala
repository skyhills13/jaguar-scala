package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import org.http4s.circe._
import io.skyhills.jaguarscala.Database
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
    def initializeCorps(): Int = {
        for (
            line <- Source.fromURL(getClass.getResource("/corp.csv")).getLines()
        ) {
            val corpName = line.split(",").head
            val corpId = "%06d".format(line.split(",").last.toInt)
            repository.insertCorp(corpId, corpName, isFavorite = false)
        }
        Source.fromURL(getClass.getResource("/corp.csv")).getLines().length
    }
}