package otus

import io.gatling.core.Predef._
import io.gatling.core.structure._
import otus.Feeders._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {

  val scn: ScenarioBuilder = scenario("Common scenario")
      .feed(users)
      .exec(Actions.getMainPage)
      .exec(Actions.getUserSession)
      .exec(Actions.login)
      .exec(Actions.getCitiesList)
      .exec(Actions.flight)
      .exec(Actions.selectflight)
      .exec(Actions.pay)
      .exec(Actions.getMainPage)

  }