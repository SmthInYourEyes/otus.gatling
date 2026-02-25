package otus

import io.gatling.core.Predef._

class FMaxCloseModel extends Simulation{
  setUp(CommonScenario()
    .inject(incrementConcurrentUsers(3)
    .times(20)
    .eachLevelLasting(30)
    .separatedByRampsLasting(10)
    .startingFrom(15)))
    .protocols(httpProtocol)
}
