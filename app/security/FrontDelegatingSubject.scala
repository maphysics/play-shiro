package security

import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.session.Session
import org.apache.shiro.subject.support.DelegatingSubject
import org.apache.shiro.mgt.SecurityManager
import play.api.Logger

/**
 *
 * @author wsargent
 * @since 4/23/13
 */
class FrontDelegatingSubject(principals:PrincipalCollection = null,
                            authenticated:Boolean = false,
                            host:String = null,
                            session:Session = null,
                            sessionCreationEnabled:Boolean = false,
                            securityManager:SecurityManager)
  extends DelegatingSubject(principals, authenticated, host, session, sessionCreationEnabled, securityManager) {

  private val logger = Logger("security.PlayDelegatingSubject")

  override def isSessionCreationEnabled = false

  /**
   * Never, ever create a session.
   *
   * @param create
   * @return
   */
  override def getSession(create:Boolean) : Session = {
    logger.debug(s"getSession: create = $create, returning null")
    null
  }

//
//  @Override
//  protected SessionContext createSessionContext() {
//      WebSessionContext wsc = new DefaultWebSessionContext();
//      String host = getHost();
//      if (StringUtils.hasText(host)) {
//          wsc.setHost(host);
//      }
//      wsc.setServletRequest(this.servletRequest);
//      wsc.setServletResponse(this.servletResponse);
//      return wsc;
//  }
}
