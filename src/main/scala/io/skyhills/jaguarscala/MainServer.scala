package io.skyhills.jaguarscala

import cats.effect.IO
import fs2.{Stream, StreamApp}
import io.skyhills.jaguarscala.service.{CorpService, TestService, TransactionService, WishService}
import org.http4s.HttpService
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object MainServer extends StreamApp[IO] {

    import scala.concurrent.ExecutionContext.Implicits.global

    def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] = ServerStream.stream
}

object ServerStream {
    def helloWorldService: HttpService[IO] = TestService.service
    def corpService: HttpService[IO] = CorpService.service
    def transactionService: HttpService[IO] = TransactionService.service
    def wishService: HttpService[IO] = WishService.service


    def stream(implicit ec: ExecutionContext): fs2.Stream[IO, StreamApp.ExitCode] =
        BlazeBuilder[IO]
            .bindHttp(8080, "0.0.0.0")
            .mountService(helloWorldService, "/")
            .mountService(corpService, "/corps")
            .mountService(transactionService,"/history")
            .mountService(wishService,"/wish")
            .serve
}
