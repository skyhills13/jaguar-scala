package io.skyhills.jaguarscala

import cats.effect.IO
import com.github.nscala_time.time.Imports.DateTime
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}

/**
  * Created by soeunpark on 2018. 6. 3...
  */
case class Corporation(corpId: Int, corpName: String, isFavorite: Boolean)

case class Transaction(txId: Int, corpId: Int, txType: String, price: Long, createdAt: DateTime)

//FIXME make txType and wishType as ENUM like thing
case class Wish(wishId: Int, wishType: String, targetPrice: Long, createdAt: DateTime)