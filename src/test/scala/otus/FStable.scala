package otus

import io.gatling.core.Predef._

class FStable extends Simulation{
  setUp(CommonScenario()
    .inject(constantUsersPerSec(36).during(3600))
    .protocols(httpProtocol))
}

