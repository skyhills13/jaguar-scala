package io.skyhills.jaguarscala

import cats.effect.{Effect, IO}
import doobie.h2.H2Transactor
import fs2.{StreamApp, Stream}
import io.skyhills.jaguarscala.repository.TestRepository
import io.skyhills.jaguarscala.service.{TestService, TransactionService, WishService}
import org.http4s.HttpService
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object MainServer extends StreamApp[IO] {

    import scala.concurrent.ExecutionContext.Implicits.global

    def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] = ServerStream.stream
}

object ServerStream {
    val xa: H2Transactor[IO] = Database.transactor()
    def helloWorldService: HttpService[IO] = new TestService(new TestRepository(xa)).service
    def transactionService: HttpService[IO] = new TransactionService[IO].service
    def wishService: HttpService[IO] = new WishService[IO].service


    def stream(implicit ec: ExecutionContext): fs2.Stream[IO, StreamApp.ExitCode] =
        BlazeBuilder[IO]
            .bindHttp(8080, "0.0.0.0")
            .mountService(helloWorldService, "/")
            .mountService(transactionService,"/history")
            .mountService(wishService,"/wish")
            .serve
}
