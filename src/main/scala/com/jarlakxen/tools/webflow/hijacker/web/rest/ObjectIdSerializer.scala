package com.jarlakxen.tools.webflow.hijacker.web.rest

import org.bson.types.ObjectId
import org.json4s._
import org.json4s.JString
import org.json4s.JString

class ObjectIdSerializer extends Serializer[ObjectId] {

	val ObjectIdClass = classOf[ObjectId]

	def deserialize( implicit format : Formats ) : PartialFunction[( TypeInfo, JValue ), ObjectId] = {
		case ( TypeInfo( ObjectIdClass, _ ), JString( v ) ) => new ObjectId( v )
	}

	def serialize( implicit formats : Formats ) : PartialFunction[Any, JValue] = {
		case x : ObjectId => JString( x.toString() )
	}

}

object ObjectIdSerializer {
	def apply() = new ObjectIdSerializer()
}