import sbt._
import sbt.Keys._
import Dependency._

scalaVersion := "2.11.1"


lazy val waffleSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "com.wyb7",
  version := "1.19",
  scalaVersion := "2.11.2",
  // disable publishing the main API jar
  publishTo := Some("Anda Nexus" at "http://183.56.128.185:8081/nexus/content/repositories/releases/"),
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
  publishArtifact in (Compile, packageDoc) := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation") //,
  //publishTo := Some(Resolver.file("file",  new File( """D:\workspace\wyb-repo\m2\release""" )) ),
  //manifestSetting
)

lazy val manifestSetting = packageOptions <+= (name, version, organization) map {
	(title, version, vendor) =>
		Package.ManifestAttributes(
			"Created-By" -> "Simple Build Tool",
			"Built-By" -> System.getProperty("user.name"),
			"Build-Jdk" -> System.getProperty("java.version"),
			"Specification-Title" -> title,
			"Specification-Version" -> version,
			"Specification-Vendor" -> vendor,
			"Implementation-Title" -> title,
			"Implementation-Version" -> version,
			"Implementation-Vendor-Id" -> vendor,
			"Implementation-Vendor" -> vendor
		)
}

lazy val waffleParent = (project in file("."))
	.settings(waffleSettings ++ Seq(
		name := "waffle-parent",
		description := "A tiny, Sinatra-like web framework for Scala"
	):_*)
	.aggregate(waffleCommons, waffleHibernate)


lazy val waffleCommons = (project in file("commons"))
	.settings(waffleSettings ++ Seq(
		name := "waffle-commons",
		description := "A tiny, Sinatra-like web framework for Scala",
		libraryDependencies ++= Seq(
			CommonsLang3,
			JodaTime, JodaConvert,
			JacksonScalaModule,
			Slf4j, Poi, SpringSecurityCore, SpringWebmvc, SpringJdbc,
			ServletApi
		)
	):_*)


lazy val waffleHibernate = (project in file("hibernate"))
	.settings(waffleSettings ++ Seq(
		name := "waffle-hibernate",
		description := "A tiny, Sinatra-like web framework for Scala",
		libraryDependencies ++= Seq(
			HibernateCore,
			Slf4j
		)
	):_*)
	.dependsOn(waffleCommons)


