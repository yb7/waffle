package com.wyb7.waffle.commons.jackson

import org.apache.commons.lang3.StringUtils
import org.slf4j.{LoggerFactory, Logger}
import org.joda.time.LocalDate
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}
import com.fasterxml.jackson.core.JsonParser

/**
 * Author: Wang Yibin
 * Date: 11-11-16
 * Time: 上午9:36
 */

class StringToJodaLocalDateDeserializer extends JsonDeserializer[LocalDate] {
    private val logger: Logger = LoggerFactory.getLogger(classOf[StringToJodaLocalDateDeserializer])

    def deserialize(jp: JsonParser, ctxt: DeserializationContext): LocalDate = {

        val text = StringUtils.trimToNull(jp.getText)
        //        try {
        if (StringUtils.isBlank(text)) {
            null
        } else {
            LocalDate.parse(text)
        }
        //        } catch {
        //            case ex: IllegalArgumentException => logger.warn("无法将字符串%s解析为日期".format(text)); null
        //        }
    }
}