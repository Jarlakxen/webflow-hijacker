package com.jarlakxen.tools.webflow.hijacker.domain.model

import org.bson.types.ObjectId
import com.jarlakxen.tools.webflow.hijacker.domain.mongo.{MongoModel, MongoObject, NamedModel}
import com.novus.salat.annotations.raw.Key
import com.mongodb.casbah.commons.MongoDBObject

case class Rule(name : String, regex : String, defaultEndpoint : String, id : ObjectId = null )  extends MongoModel[Rule] {
	
	def copy(id : ObjectId) = new Rule(name, regex, defaultEndpoint, id);
	
}

object Rule extends MongoObject[Rule] with NamedModel[Rule] {

	def findMatchingWith( url: String ) : Option[Rule] = _dao.findOne( MongoDBObject("$where" -> ("RegExp(this.regex).test('"+url+"')")) )
	
}