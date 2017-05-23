import org.specs2.mutable._
/**
  * Created by zhaoshoulai on 2017/5/23.
  */
class ProductSpec extends Specification{

  "The 'product' string" should {
    "contain seven characters" in {
      "product" must have size(8)
    }
  }
}
