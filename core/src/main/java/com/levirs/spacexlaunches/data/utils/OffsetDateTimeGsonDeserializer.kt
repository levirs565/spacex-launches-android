package com.levirs.spacexlaunches.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class OffsetDateTimeGsonDeserializer : JsonDeserializer<OffsetDateTime> {
    private val mFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): OffsetDateTime? {
        return json?.let {
            OffsetDateTime.parse(it.asJsonPrimitive.asString, mFormatter)
        }
    }
}
