package com.jarlakxen.tools.webflow.hijacker.web.dispatcher

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import org.scalatra.ScalatraServlet
import dispatch.{ url => $url, Http => $http, _ }
import org.scalatra.CorsSupport
import com.jarlakxen.tools.webflow.hijacker.domain.model.Rule

class DispatcherServlet extends ScalatraServlet with CorsSupport {

	options( "/*", request.getHeader( "X-FORCE-HIJACK" ) != "ADMIN" ) {
		response.setHeader( "Access-Control-Allow-Headers", request.getHeader( "Access-Control-Request-Headers" ) );
	}

	get( "/*", request.getHeader( "X-FORCE-HIJACK" ) != "ADMIN" ) {

		var host = formattedHost(request.getHeader( "X-FORCE-HIJACK" ));
		
		if( host == null){
			host = Rule.findMatchingWith(request.getRequestURI()) match {
				case Some(v) => formattedHost(v.defaultEndpoint)
				case None => halt(404)
			}
		}
		
		val redirectRequest = $url( host + request.getRequestURI() )

		request.getHeaderNames().foreach { name =>
			name match {
				case "User-Agent" =>
				case "X-FORCE-HIJACK" => redirectRequest.addHeader( name, request.getHeader( name ) )
				//case "Host" => redirectRequest.addHeader(name, request.getHeader("BHJ_DOMAIN"))
				case _ =>
			}
		}

		request.getParameterNames().foreach { name =>
			redirectRequest.addQueryParameter( name, request.getParameter( name ) )
		}

		val content = $http( redirectRequest > { str =>
			response.setContentType( str.getContentType() );
			str.getResponseBody();
		} )

		content()
	}
	
	def formattedHost(host: String) = host match {
		case null => null
		case value if value.endsWith("/") => value.substring(0, value.length() -1 )
		case _ => host
	}
}