package com.wyb7.waffle.commons.jackson

import org.apache.commons.lang3.StringUtils
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}


class StringToBigDecimal extends JsonDeserializer[BigDecimal] {
    def deserialize(jp: JsonParser, ctxt: DeserializationContext): BigDecimal = {
        val text = StringUtils.trimToNull(jp.getText)
        //        try {
        if (StringUtils.isBlank(text)) {
            null
        } else {
            BigDecimal(text)
        }
    }
}
