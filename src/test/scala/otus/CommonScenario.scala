package otus

import io.gatling.core.Predef._
import io.gatling.core.structure._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {


  val scn: ScenarioBuilder = scenario("Common scenario")
      .exec(Actions.getMainPage)
      .pause(1,2)
      .exec(Actions.getUserSession)
      .pause(1,2)
      .exec(Actions.login)
      .pause(1,2)
      .exec(Actions.getCitiesList)
      .pause(1,2)
      .exec(Actions.flight)
      .pause(1,2)
      .exec(Actions.selectflight)
      .pause(1,2)
      .exec(Actions.pay)
      .pause(1,2)
      .exec(Actions.getMainPage)

  }