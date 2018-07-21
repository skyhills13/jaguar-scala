package io.skyhills.jaguarscala.service

import cats.data.ValidatedNel
import org.http4s.QueryParamDecoder
import org.http4s.dsl.io._

/**
  * Created by soeunpark on 2018. 7. 22...
  */

object OptionalIsFavoriteMatcher extends OptionalQueryParamDecoderMatcher[Boolean]("isFavorite")
object IsFavoriteMatcher extends QueryParamDecoderMatcher[Boolean]("isFavorite")

object CountMatcher extends OptionalQueryParamDecoderMatcher[Int]("count")

