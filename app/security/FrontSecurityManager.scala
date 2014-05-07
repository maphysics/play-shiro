package security

import org.apache.shiro.mgt._
import org.apache.shiro.subject.SubjectContext

/**
 * This is a partial copy of DefaultWebSecurityManager.
 *
 * @author wsargent
 * @since 4/22/13
 */
class FrontSecurityManager extends DefaultSecurityManager {

  setSubjectFactory(new FrontSubjectFactory())

  // Not applicable as we're not using the java.servlet API
  //this.subjectDAO.asInstanceOf[DefaultSubjectDAO].setSessionStorageEvaluator(new DefaultWebSessionStorageEvaluator())
  //setRememberMeManager(new CookieRememberMeManager())
  //setSessionManager(new ServletContainerSessionManager())

  override protected def createSubjectContext = new FrontSubjectContext()

  override protected def copy(subjectContext:SubjectContext) : SubjectContext = {
    if (subjectContext.isInstanceOf[FrontSubjectContext]) {
      return new FrontSubjectContext(subjectContext)
    }
    super.copy(subjectContext)
  }

}
