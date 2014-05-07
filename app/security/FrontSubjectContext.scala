package security

import org.apache.shiro.subject.support.DefaultSubjectContext
import play.api.mvc.RequestHeader
import org.apache.shiro.subject.SubjectContext

/**
 * A subject context that extends the default, but does not have session support enabled.
 *
 *
 * @author wsargent
 * @since 4/22/13
 */
class FrontSubjectContext(ctx:SubjectContext = null) extends DefaultSubjectContext(ctx) {

  override def getSession = null

  override def isSessionCreationEnabled = false
}
