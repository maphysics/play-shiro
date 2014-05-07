import models.User
import org.apache.shiro.mgt.{DefaultSubjectFactory, DefaultSessionStorageEvaluator, DefaultSubjectDAO, DefaultSecurityManager}
import play.api._

import play.api.mvc.{Handler, RequestHeader}
import security._

/**
 *
 * @author wsargent
 * @since 1/8/12
 */
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    ShiroConfig.initialize()
    InitialData.insert()
  }

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    super.onRouteRequest(request)
  }
}

object ShiroConfig {

  def initialize() {
    val sampleRealm = new FrontRealm()
    val securityManager = new FrontSecurityManager()
    securityManager.setRealm(sampleRealm)

    // Turn off session storage for better "stateless" management.
    // https://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%2528Sessionless%2529
    val subjectDAO = securityManager.getSubjectDAO.asInstanceOf[DefaultSubjectDAO]
    val sessionStorageEvaluator = subjectDAO.getSessionStorageEvaluator.asInstanceOf[DefaultSessionStorageEvaluator]

    sessionStorageEvaluator.setSessionStorageEnabled(false)

    org.apache.shiro.SecurityUtils.setSecurityManager(securityManager)
  }
}


/**
 * Initial set of data to be imported
 * in the sample application.
 */
object InitialData {

  def insert() = {

    if(User.findAll.isEmpty) {

      Seq(
        User("admin@example.com", "admin")
      ).foreach(User.create)
    }

  }

}
