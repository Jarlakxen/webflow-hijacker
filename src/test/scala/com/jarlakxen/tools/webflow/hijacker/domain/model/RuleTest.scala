package com.jarlakxen.tools.webflow.hijacker.domain.model

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import org.specs2.mutable.Before
import com.jarlakxen.tools.webflow.hijacker.domain.mongo.MongoStore

@RunWith( classOf[JUnitRunner] )
class RuleTest extends Specification {

	implicit def context = new Before { 
		def before = {
				System.setProperty("db.name", "test-webflow-hijacker")
				MongoStore dropDatabase
		} 
	}
	
	"Rule" should {

		"persist" in {
			var rule = new Rule("web", "*/web/*", "http://localhost:8080/" )

			rule = rule saveOrUpdate

			var aux = Rule findByName "web"

			aux match {
				case None => failure( "Value not stored" )
				case Some( x ) => {
					x.name must be equalTo ( "web" )
					x.id must not beNull
				}
			}

			Rule.count must be_==( 1 )

			aux.get.delete

			Rule.count must be_==( 0 )
		}
	}
	
	"Rule" should {

		"match with url http://test/web/test" in {
			var rule = new Rule("web", "\\/web\\/", "http://localhost:8080/" )

			rule saveOrUpdate

			var matchRule = Rule findMatchingWith(" http://test/web/test");
			
			matchRule match {
				case None => failure( "Url doesn't match with anything" )
				case Some( x ) => {
					x.name must be equalTo ( "web" )
				}
			}
			
			matchRule.get.delete
			
			Rule.count must be_==( 0 )
		}
	}
	
}