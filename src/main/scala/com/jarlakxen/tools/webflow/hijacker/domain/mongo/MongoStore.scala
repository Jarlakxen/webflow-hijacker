package com.jarlakxen.tools.webflow.hijacker.domain.mongo

import scala.collection.mutable.Map
import com.mongodb.casbah.Imports._
import com.mongodb.{ ServerAddress, Mongo }
import com.mongodb.casbah.{ MongoDB, MongoOptions, MongoConnection }
import com.jarlakxen.tools.webflow.hijacker.domain.utils.Config

object MongoStore {

	private lazy val MongoDBCollections = connect( Config.getString("db.name", "webflow-hijacker") )

	private val collections : Map[String, MongoCollection] = Map()

	private def connect( name : String ) : MongoDB = {

		val opts = MongoOptions( autoConnectRetry = true, connectionsPerHost = Config.getInt("db.connectionsPerHost", 20) )
		
		val connection = new MongoConnection( new Mongo( new ServerAddress( Config.getString("db.host", "localhost"), Config.getInt("db.port", 27017) ), opts ) )( name )
		
		if( Config.hasKey("db.user") && Config.hasKey("db.password") ){
			connection.authenticate(Config.getString("db.user"), Config.getString("db.password"));
		}
		
		connection.setWriteConcern(WriteConcern.FsyncSafe)
		
		connection
	}
	
	def dropDatabase = MongoDBCollections dropDatabase

	def Collection( name : String ) = this.synchronized {

		var collection = collections.get( name )

		collection match {
			case Some(value) => value
			
			case None => {
					val newCollection = MongoDBCollections( name )
					collections( name ) = newCollection
					newCollection
			}
		}
	}
}