package org.hyrical.mimic.profiles.adapter

import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.google.protobuf.util.JsonFormat
import org.hyrical.mimic.protocol.Profile
import org.hyrical.mimic.protocol.Rank

class ProfileAdapter : TypeAdapter<Profile>() {
    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for `value`.
     *
     * @param value the Java object to write. May be null.
     */
    override fun write(out: JsonWriter?, value: Profile?) {
        out?.jsonValue(JsonFormat.printer().print(value));
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @return the converted Java object. May be null.
     */
    override fun read(`in`: JsonReader?): Profile {
        val profileBuilder = Profile.newBuilder()

        JsonFormat.parser().merge(JsonParser.parseReader(`in`).toString(), profileBuilder)

        return profileBuilder.build()
    }
}