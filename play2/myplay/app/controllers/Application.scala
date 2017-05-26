package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello = Action {
    Ok("Hello World")
  }

  def echo(msg:String) = Action {
    Ok("Echoing "+msg)
  }

  def helloRedirect() = Action {
    Redirect(routes.Application.echo("HelloWorldV2"))
  }

  private val productMap = Map(1-> "Keyboard", 2-> "Mouse", 3->"Monitor")

  def listProducts() = Action {
    Ok(views.html.products(productMap.values.toSeq))
  }

  def listProductsAsTXT = Action{
    Ok(views.txt.products(productMap))
  }

  def listProductsAsXML = Action{
    Ok(views.xml.products(productMap))
  }

  //curl -v http://localhost:9000/header_example
  def modifyHeaders = Action{
    Ok("Header Modification examples")
      .withHeaders(
        play.api.http.HeaderNames.ETAG -> "foo_scala"
      )
  }
  //curl -v http://localhost:9000/cookie_example
  def modifyCookies = Action{
    val cookie = Cookie("source", "tw", Some(60*60))
    Ok("Cookie Modification examples")
      .withCookies(cookie)
  }

  def modifySession = Action{ request =>
    val sessionVar = "user_pref"
    request.session.get(sessionVar) match{
      case Some(userPref) => {
        Ok("Found userPref: %s".format(userPref))
      }
      case None =>{
        Ok("Setting session var %s".format(sessionVar))
        .withSession(
          sessionVar -> "tw"
        )
      }
    }
  }

  //需要验证通过
  def dashboard = AuthAction {
    Ok("User dashboard")
  }
  def login = Action {
    Ok("Please login")
  }


  object AuthAction extends ActionBuilder[Request]{
    //import play.api.mvc.Results._
    import scala.concurrent.Future

    def invokeBlock[A](request: Request[A], block: (Request[A]) =>
      Future[Result]) = {
      request.cookies.get("auth") match {
        case Some(authCookie) => {
          Logger.info("Cookie: " + authCookie)
          block(request)
        }
        case None => {
          Logger.info("Redirecting to login page")
          Future.successful(Redirect(routes.Application.login()))
        }
      }
    }
  }


}