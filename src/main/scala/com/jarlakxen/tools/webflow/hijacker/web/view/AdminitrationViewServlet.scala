package com.jarlakxen.tools.webflow.hijacker.web.view

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport
import com.jarlakxen.tools.webflow.hijacker.domain.model.Rule

class AdminitrationViewServlet extends ScalatraServlet with ScalateSupport {
	
	before() {
		contentType = "text/html"
	}
	
	get("/admin"){
		mustache("main")
	}
}