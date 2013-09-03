package com.wyb7.waffle.commons.jackson

import org.joda.time.LocalDate
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{SerializerProvider, JsonSerializer}

class JodaLocalDateSerializer extends JsonSerializer[LocalDate] {
    def serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toString())
    }
}