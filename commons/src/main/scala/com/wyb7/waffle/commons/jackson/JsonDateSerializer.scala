package com.wyb7.waffle.commons.jackson

import java.util.Date;
import java.text.SimpleDateFormat
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{SerializerProvider, JsonSerializer}
;

class JsonDateSerializer extends JsonSerializer[Date] {

    private val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    override def serialize(date: Date, gen: JsonGenerator, provider: SerializerProvider) =
        gen.writeString(dateFormat.format(date))
}