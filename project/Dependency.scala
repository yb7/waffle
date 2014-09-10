import sbt._

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
	val JacksonScalaModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.2" % "provided"
}