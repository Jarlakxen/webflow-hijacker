import sbt._
import Keys._

import com.github.siasia._

import com.typesafe.sbteclipse.plugin.EclipsePlugin._

import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._

import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object WebflowHijacker extends Build {



    val Organization = "com.jarlakxen.tools"
    val Name = "Webflow Hijacker"
    val Version = "1.0-SNAPSHOT"

    val ScalatraVersion = "2.2.1"

    lazy val project = Project (
        "webflow-hijacker",
        file("."),
        settings =  Project.defaultSettings ++
                    ScalatraPlugin.scalatraWithJRebel ++ 
                    scalateSettings ++ /*
                    Seq(
                        EclipseKeys.projectFlavor := EclipseProjectFlavor.Scala,
                        EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource, 
                        EclipseKeys.withSource := true, 
                        EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16) ) ++ */
                    Seq(
                        organization := Organization,
                        name := Name,
                        version := Version,
                        scalaVersion := "2.9.2") ++
                    Seq(
                        resolvers ++= Seq("OSS Sonatype" at "https://oss.sonatype.org/content/groups/scala-tools/",
                                          "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
                                          "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases/",
                                          "TypeSafe Artifactory Releases" at "http://repo.typesafe.com/typesafe/simple/akka-releases-cache/")) ++
                    Seq(
                        libraryDependencies ++= Seq(

                            // Scalatra
                            "org.scalatra" %% "scalatra" % ScalatraVersion withSources() withJavadoc(),
                            "org.scalatra" %% "scalatra-scalate" % ScalatraVersion withSources() withJavadoc(),
                            "org.scalatra" %% "scalatra-json" % ScalatraVersion withSources(),
                            "org.json4s" %% "json4s-jackson" % "3.2.4" withSources(),
                            "org.json4s" %% "json4s-ext" % "3.2.4" withSources(),
                            "ch.qos.logback" % "logback-classic" % "1.0.9" % "runtime",

                            // HTTP Dispatcher
                            "net.databinder.dispatch" %% "dispatch-core" % "0.9.5" withSources() withJavadoc(),

                            // Salat
                            "com.novus" %% "salat" % "1.9.2-SNAPSHOT",

                            // Jetty
                            "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),
                            "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",

                            // Testing
                            "org.specs2" %% "specs2" % "1.12.3" % "test" withSources() withJavadoc(),
                            "junit" % "junit" % "4.8.1" % "test"
                        )
                    )
        )
}
