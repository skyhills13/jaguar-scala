package io.skyhills.jaguarscala

import cats.effect.{Effect, IO}
import doobie.h2.H2Transactor
import fs2.{StreamApp, Stream}
import io.skyhills.jaguarscala.repository.TestRepository
import io.skyhills.jaguarscala.service.{HelloWorldService, TransactionService, WishService}
import org.http4s.HttpService
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object HelloWorldServer extends StreamApp[IO] {

    import scala.concurrent.ExecutionContext.Implicits.global

    def stream(args: List[String], requestShutdown: IO[Unit]) = ServerStream.stream[IO]
}

object ServerStream {
    val xa: H2Transactor[IO] = Database.transactor()
    def helloWorldService[F[_] : Effect]: HttpService[F] = new HelloWorldService(new TestRepository(xa)).service
    def transactionService[F[_] : Effect]: HttpService[F] = new TransactionService[F].service
    def wishService[F[_] : Effect]: HttpService[F] = new WishService[F].service


    def stream[F[_] : Effect](implicit ec: ExecutionContext): fs2.Stream[F, StreamApp.ExitCode] =
        BlazeBuilder[F]
            .bindHttp(8080, "0.0.0.0")
            .mountService(helloWorldService, "/")
            .mountService(transactionService,"/history")
            .mountService(wishService,"/wish")
            .serve
}
