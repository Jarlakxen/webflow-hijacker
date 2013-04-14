import org.scalatra._
import javax.servlet.ServletContext
import com.jarlakxen.tools.webflow.hijacker.web.view._
import com.jarlakxen.tools.webflow.hijacker.web.rest._
import com.jarlakxen.tools.webflow.hijacker.web.dispatcher.DispatcherServlet

class ScalatraBootstrap extends LifeCycle {
	
	override def init( context : ServletContext ) {
		
		context.mount( new RuleServlet, "/hijack/rest/rule/*" )
		context.mount( new AdminitrationViewServlet, "/hijack/*" )
		context.mount( new DispatcherServlet, "/*" )
	}
	
}