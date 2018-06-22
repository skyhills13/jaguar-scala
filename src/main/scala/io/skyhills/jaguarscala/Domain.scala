package io.skyhills.jaguarscala

import cats.effect.IO
import com.github.nscala_time.time.Imports.DateTime
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonOf, jsonEncoderOf}

/**
  * Created by soeunpark on 2018. 6. 3...
  */
case class Corporation(
    corpId: Int,
    corpName: String
)
object Corporation {
    implicit val corporationDecoder: EntityDecoder[IO, Corporation] = jsonOf[IO, Corporation]
    implicit val corporationEncoder: EntityEncoder[IO, Corporation] = jsonEncoderOf[IO, Corporation]

    def getCorporation(corpId: Int): IO[Corporation] = ???
}


case class Transaction(
    txId: Int,
    corpId : Int,
    txType: String,
    price: Long,
    date: DateTime
)
object Transaction {
    implicit val transactionDecoder: EntityDecoder[IO, Transaction] = jsonOf[IO, Transaction]
    implicit val transactionEncoder: EntityEncoder[IO, Transaction] = jsonEncoderOf[IO, Transaction]
    implicit val transactionsDecoder: EntityDecoder[IO, Seq[Transaction]] = jsonOf[IO, Seq[Transaction]]
    implicit val transactionsEncoder: EntityEncoder[IO, Seq[Transaction]] = jsonEncoderOf[IO, Seq[Transaction]]

    def getTransaction(corpId: Int): IO[Transaction] = ???
    def getTransactions(corpId: Int): IO[Seq[Transaction]] = ???
}