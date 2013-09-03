package com.wyb7.waffle.commons.jackson

import org.springframework.beans.factory.FactoryBean
import org.joda.time.LocalDate
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.core.Version

/**
 * Author: Wang Yibin
 * Date: 11-5-8
 * Time: 下午8:36
 */

class ObjectMapperFactoryBean extends FactoryBean[ObjectMapper] {
    def isSingleton = true

    def getObjectType = classOf[ObjectMapper]

    def getObject = ObjectMapperFactoryBean.objectMapper
}

object ObjectMapperFactoryBean {
    val objectMapper = new ObjectMapper
    objectMapper.registerModule(new DefaultScalaModule)
    val simpleModule = new SimpleModule("SimpleModule", new Version(1, 0, 0, null, "wyb", "wyb"))
            .addDeserializer(classOf[String], new StringTrimToNullDeserializer)
            .addDeserializer(classOf[LocalDate], new StringToJodaLocalDateDeserializer)
            .addDeserializer(classOf[BigDecimal], new StringToBigDecimal)
            .addSerializer(classOf[LocalDate], new JodaLocalDateSerializer)
    objectMapper.registerModule(simpleModule)
}