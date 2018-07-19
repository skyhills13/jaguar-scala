package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import org.http4s.circe._
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl
import io.skyhills.jaguarscala.{Corporation, Database}
import io.skyhills.jaguarscala.repository.CorpRepository

import scala.io.Source

/**
  * Created by soeunpark on 2018. 7. 17..
  */
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
        val corpList: List[Corporation] = Source.fromURL(getClass.getResource("/corp.csv")).getLines()
            .map(c => Corporation(
                c.split(",").head,
                "%06d".format(c.split(",").last.toInt),
                isFavorite = false))
            .toList
        repository.initCorps(corpList)
    }
}
