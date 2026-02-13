package otus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Debug extends Simulation{
  setUp(CommonScenario().inject(atOnceUsers(1)))
    .protocols(httpProtocol.proxy(Proxy("127.0.0.1", 8888)))
    .assertions(global.responseTime.max.lt(1000))
    .maxDuration(1000)
}
