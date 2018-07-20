package io.skyhills.jaguarscala.service

import cats.effect.IO
import io.circe.Json
import io.skyhills.jaguarscala.Database
import io.skyhills.jaguarscala.repository.CorpRepository
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

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
        var count = 0
        for (
            line <- Source.fromURL(getClass.getResource("/corp.csv")).getLines()
        ) {
            val corpProps = line.split(",")
            val corpName = corpProps.head
            val corpId = "%06d".format(corpProps.last.toInt)
            val insertedRow = repository.insertCorp(corpId, corpName, isFavorite = false)
            count += insertedRow
        }
        count
    }
//    def initializeCorps(): Int = {
//        val corpList: List[Corporation] = Source.fromURL(getClass.getResource("/corp.csv")).getLines()
//            .map(c => Corporation(
//                c.split(",").head,
//                "%06d".format(c.split(",").last.toInt),
//                isFavorite = false))
//            .toList
//        repository.initCorps(corpList)
//    }
}
