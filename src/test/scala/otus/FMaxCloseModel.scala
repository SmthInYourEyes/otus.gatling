package otus

import io.gatling.core.Predef._

class FMaxCloseModel extends Simulation{
  setUp(CommonScenario()
    .inject(incrementUsersPerSec(1.0)
    .times(30)
    .eachLevelLasting(10)
    .separatedByRampsLasting(5)
    .startingFrom(1)))
    .protocols(httpProtocol)
}
