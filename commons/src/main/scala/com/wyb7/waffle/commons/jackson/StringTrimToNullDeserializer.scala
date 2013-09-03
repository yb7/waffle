package com.wyb7.waffle.commons.jackson

import org.apache.commons.lang3.StringUtils
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}
import com.fasterxml.jackson.core.JsonParser

/**
 * Author: Wang Yibin
 * Date: 11-11-16
 * Time: 上午9:36
 */

class StringTrimToNullDeserializer extends JsonDeserializer[String] {
    def deserialize(jp: JsonParser, ctxt: DeserializationContext): String = {
        StringUtils.trimToNull(jp.getText)
    }
}