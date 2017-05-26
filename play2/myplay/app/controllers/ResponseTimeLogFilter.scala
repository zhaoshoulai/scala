package controllers

/**
  * Created by qzhd on 2017/5/26.
  */
import play.api.Logger
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
object ResponseTimeLogFilter extends EssentialFilter {

  def apply(nextFilter: EssentialAction) = new EssentialAction {
    def apply(requestHeader: RequestHeader) = {
      val startTime = System.currentTimeMillis
      nextFilter(requestHeader).map { result =>
        val currDate = new java.util.Date
        val responseTime = (currDate.getTime() - startTime) / 1000F
        Logger.info(s"${requestHeader.remoteAddress} -[${currDate}] - ${requestHeader.method} ${requestHeader.uri}" +
          s" ${result.header.status} ${responseTime}")
        result
      }
    }
  }
}