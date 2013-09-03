import sbt._
import Keys._

object WaffleBuild extends Build {
    import Dependency._

    lazy val waffleSettings = Defaults.defaultSettings ++ Seq(
        organization := "com.wyb7",
        version := "1.19",
        scalaVersion := "2.10.0",
        // disable publishing the main API jar
        publishArtifact in (Compile, packageDoc) := false,
        scalacOptions ++= Seq("-unchecked", "-deprecation"),
        publishTo := Some(Resolver.file("file",  new File( """D:\workspace\wyb-repo\m2\release""" )) ),
        manifestSetting
    ) ++ net.virtualvoid.sbt.graph.Plugin.graphSettings

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

    lazy val waffleProject = Project(
        id = "waffle-project",
        base = file("."),
        settings = waffleSettings ++ Seq(
            description := "A tiny, Sinatra-like web framework for Scala"
        ),
        aggregate = Seq(waffleCommons, waffleHibernate)
    )

    lazy val waffleCommons = Project(
        id = "waffle-commons",
        base = file("commons"),
        settings = waffleSettings ++ Seq(
            description := "A tiny, Sinatra-like web framework for Scala",
            libraryDependencies ++= Seq(
                CommonsLang3,
                JodaTime, JodaConvert,
                JacksonScalaModule,
                Slf4j, Poi, SpringSecurityCore, SpringWebmvc, SpringJdbc,
                ServletApi
            )
        )
    )

    lazy val waffleHibernate = Project(
        id = "waffle-hibernate",
        base = file("hibernate"),
        settings = waffleSettings ++ Seq(
            description := "A tiny, Sinatra-like web framework for Scala",
            libraryDependencies ++= Seq(
                HibernateCore,
                Slf4j
            )
        ),
        dependencies = Seq(waffleCommons)
    )

    object Dependency {
        val ServletApi = "javax.servlet" % "servlet-api" % "2.5" % "provided"
        val CommonsLang3 = "org.apache.commons" % "commons-lang3" % "3.1"
        val HibernateCore = "org.hibernate" % "hibernate-core" % "4.1.0.Final"
        val JodaTime = "joda-time" % "joda-time" % "2.0"
        val JodaConvert = "org.joda" % "joda-convert" % "1.1"
        val Slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"
        val Poi = "org.apache.poi" % "poi" % "3.7" % "provided"
        val SpringSecurityCore = "org.springframework.security" % "spring-security-core" % "3.1.0.RELEASE" % "provided"
        val SpringWebmvc = "org.springframework" % "spring-webmvc" % "3.1.0.RELEASE" % "provided"
        val SpringJdbc = "org.springframework" % "spring-jdbc" % "3.1.0.RELEASE" % "provided"
//        val JacksonMapper = "com.fasterxml.jackson" % "jackson-mapper-asl" % "1.9.5" % "provided"
        val JacksonScalaModule = "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2" % "provided"
    }

}