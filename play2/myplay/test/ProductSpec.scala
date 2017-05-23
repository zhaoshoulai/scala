import models.Product
import org.specs2.mutable._
import play.api.test.{FakeRequest, WithApplication}
import play.api.test.Helpers._



/**
  * Created by zhaoshoulai on 2017/5/23.
  */
class ProductSpec extends Specification{

  "controller.Application" should {
    "respond with xml for /product.xml requests" in new WithApplication() {
      val result = controllers.Application.listProductsAsXML()(FakeRequest())
      status(result) must equalTo(OK)
      contentType(result) must beSome("application/xml")
      contentAsString(result) must contain("products")
    }
  }

  "models.Product" should {
    "create a product with save()" in new WithApplication() {
      val product = Product(4, "Apple")
      val productId = Product.save(product)

      productId must not be None
    }
  }

  "The 'product' string" should {
    "contain seven characters" in {
      "product" must have size(7)
    }
  }


}
