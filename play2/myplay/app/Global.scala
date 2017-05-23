import play.api.GlobalSettings
import play.api.mvc.WithFilters
import play.filters.csrf._


/**
  * Created by zhaoshoulai on 2017/5/23.
  */
object Global extends WithFilters(CSRFFilter()) with GlobalSettings{

}
