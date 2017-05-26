package controllers
import play.api.data._
import play.api.data.Forms._
import models._
import play.api.mvc._
/**
  * Created by zhaoshoulai on 2017/5/23.
  */

object Products extends Controller{
  val form = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText(minLength = 3, maxLength = 100)
    )(Product.apply)(Product.unapply)
  )
  //TODO implicit CSRF
  def create = Action { implicit request =>
    Ok(views.html.product.form(form))

  }

  def postForm = Action {implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.product.form(formWithErrors))
      },
      product=>{Product.save(product)
        Redirect(routes.Products.create).flashing("success" -> "Product saved!")
      }
    )
  }

}

