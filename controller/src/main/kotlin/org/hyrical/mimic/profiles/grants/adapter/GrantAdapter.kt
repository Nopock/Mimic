package org.hyrical.mimic.profiles.grants.adapter

import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.google.protobuf.util.JsonFormat
import org.hyrical.mimic.protocol.Grant
import org.hyrical.mimic.protocol.Punishment

class GrantAdapter : TypeAdapter<Grant>() {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for `value`.
     *
     * @param value the Java object to write. May be null.
     */
    override fun write(out: JsonWriter?, value: Grant?) {
        out?.jsonValue(JsonFormat.printer().print(value));
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @return the converted Java object. May be null.
     */
    override fun read(`in`: JsonReader?): Grant {
        val grantBuilder = Grant.newBuilder()

        JsonFormat.parser().merge(JsonParser.parseReader(`in`).toString(), grantBuilder)

        return grantBuilder.build()
    }
}