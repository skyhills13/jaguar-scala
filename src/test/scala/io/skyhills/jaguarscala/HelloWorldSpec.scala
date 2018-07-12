package io.skyhills.jaguarscala

import cats.effect.IO
import io.skyhills.jaguarscala.repository.TestRepository
import io.skyhills.jaguarscala.service.TestService
import org.http4s._
import org.http4s.implicits._
import org.specs2.matcher.MatchResult

class HelloWorldSpec extends org.specs2.mutable.Specification {

    "HelloWorld" >> {
        "return 200" >> {
            uriReturns200()
        }
        "return hello world" >> {
            uriReturnsHelloWorld()
        }
    }

    private[this] val retHelloWorld: Response[IO] = {
        val getHW = Request[IO](Method.GET, Uri.uri("/hello/world"))
        TestService.service.orNotFound(getHW).unsafeRunSync()
    }

    private[this] def uriReturns200(): MatchResult[Status] =
        retHelloWorld.status must beEqualTo(Status.Ok)

    private[this] def uriReturnsHelloWorld(): MatchResult[String] =
        retHelloWorld.as[String].unsafeRunSync() must beEqualTo("{\"message\":\"Hello, world\"}")
}
