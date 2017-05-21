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
}