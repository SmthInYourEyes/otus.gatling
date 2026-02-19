package otus

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Actions {

  val getMainPage: HttpRequestBuilder = http("getMainPage")
    .get("/webtours/")
    .check(status is 200)


  val getUserSession: HttpRequestBuilder = http("getUserSession")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(css("input[name='userSession']", "value").saveAs("userSession"))
    .check(status is 200)

  val login: HttpRequestBuilder = http("login")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "#{userSession}")
    .formParam("username", "shustovevg")
    .formParam("password", "qwerty")
    .formParam("login.x", "47")
    .formParam("login.y", "6")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)

  val getCitiesList: HttpRequestBuilder = http("getCitiesList")
    .get("/cgi-bin/reservations.pl")
    .check(css("select[name='depart'] option", "value").findRandom.saveAs("randomCity1"))
    .check(css("select[name='depart'] option", "value").findRandom.saveAs("randomCity2"))
    .check(status is 200)

  val flight: HttpRequestBuilder = http("flight")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{randomCity1}")
    .formParam("departDate", "02/25/2026")
    .formParam("arrive", "#{randomCity2}")
    .formParam("returnDate", "02/26/2026")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "35")
    .formParam("findFlights.y", "2")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(status is 200)
    .check(regex("""outboundFlight" value="([^"]+)"""").findRandom.saveAs("randomOutboundFlight"))

  val selectflight: HttpRequestBuilder = http("selectflight")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{randomOutboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "1")
    .formParam("reserveFlights.y", "1")
    .check(status is 200)


  val pay: HttpRequestBuilder = http("pay")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "Sydney")
    .formParam("lastName", "Sweeney")
    .formParam("address1", "111")
    .formParam("address2", "222")
    .formParam("pass1", "Sydney Sweeney")
    .formParam("creditCard", "123")
    .formParam("expDate", "313")
    .formParam("oldCCOption", "")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("address2", "Coach")
    .formParam("outboundFlight", "#{randomOutboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "70")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)
}
