package com.jarlakxen.tools.webflow.hijacker.web.rest

import com.jarlakxen.tools.webflow.hijacker.domain.model.Rule

class RuleServlet extends RestService {
	
	get( "/" ) {
		Rule findAll
	}

	get( "/:id" ) {
		Rule findById( params( "id" ) ) get
	}

	post( "/" ) {
		var newRule = extract[Rule]

		newRule saveOrUpdate
	}

	delete( "/:id" ) {
		val rule = Rule findById( params( "id" ) ) get

		rule delete
	}
}