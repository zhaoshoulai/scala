import play.api.{Application, GlobalSettings, Logger}
import play.api.mvc.WithFilters
import controllers.ResponseTimeLogFilter

/**
  * Created by zhaoshoulai on 2017/5/23.
  */
object Global extends WithFilters(ResponseTimeLogFilter) with GlobalSettings{
  override def onStart(app: Application) {
    Logger.info("Application has started")
  }
  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }
}
