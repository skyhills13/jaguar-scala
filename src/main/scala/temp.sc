import doobie._


import doobie.ConnectionIO
import cats.data._
import cats.implicits._

//"hello" + "world"
//"hello" + "world"
//"hello " ++ "world"

val program1 = 42.pure[ConnectionIO]
