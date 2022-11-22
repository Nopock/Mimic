package org.hyrical.store.serializers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import com.google.gson.TypeAdapter
import org.hyrical.store.serializers.impl.GsonSerializer

object Serializers {
    var activeSerialize: Serializer = GsonSerializer()

    fun bind(serializer: Serializer) {
        activeSerialize = serializer
    }

    inline fun <reified T> registerTypeAdapter(typeAdapter: TypeAdapter<T>) {
        val serialize = this.activeSerialize as GsonSerializer
        serialize.gsonBuilder.registerTypeAdapter(T::class.java, typeAdapter)
    }

    fun buildGSON() {
        val serialize = this.activeSerialize as GsonSerializer
        serialize.gson = serialize.gsonBuilder.create()
    }
}