package controllers

import play.api.data._
import play.api.mvc.{Action, Controller, PathBindable}
import play.api.data.Forms._
/**
  * Created by qzhd on 2017/5/26.
  */
case class ProductTest(sku: String, title: String)

object ProductTest {

  implicit def pathBinder(implicit stringBinder: PathBindable[String]) = new PathBindable[ProductTest] {
    override def bind(key: String, value: String): Either[String, ProductTest] = {
      for {
        sku <- stringBinder.bind(key, value).right
        product <- productMap.get(sku).toRight("Product not found").right
      } yield product
    }
    override def unbind(key: String, product: ProductTest): String = {
      stringBinder.unbind(key, product.sku)
    }
  }

  def add(product: ProductTest) = productMap += (product.sku -> product)

  val productMap = scala.collection.mutable.Map(
    "ABC" -> ProductTest("ABC", "8-Port Switch"),
    "DEF" -> ProductTest("DEF", "16-Port Switch"),
    "GHI" -> ProductTest("GHI", "24-Port Switch")
  )
}


object ProductTests extends Controller {

  val productForm: Form[ProductTest] = Form(
    mapping(
      "sku" -> nonEmptyText,
      "title" -> nonEmptyText
    )(ProductTest.apply)(ProductTest.unapply)
  )
  def edit(product: ProductTest) = Action {
    Ok(views.html.productTests.form(product.sku, productForm.fill(product)))
  }
  def update(sku: String) = Action {
    Ok("Received update request")
}
}