package org.hyrical.store.repository.impl

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import org.hyrical.store.DataStoreController
import org.hyrical.store.constants.DataTypeResources
import org.hyrical.store.repository.Repository
import org.hyrical.store.serializers.Serializers

class MongoRepository<T>(private val controller: DataStoreController<T>) : Repository<T> {

    private val id = controller.classType.simpleName

    private val serializer = Serializers.activeSerialize

    var collection: MongoCollection<Document> = if (DataTypeResources.mongoCollections.containsKey(id)) {
        DataTypeResources.mongoCollections[id]!!
    } else {
        val collection = DataTypeResources.mongoDatabase!!.getCollection(id)
        DataTypeResources.mongoCollections[id] = collection
        collection
    }

    override fun search(id: String): T? {
        return serializer.deserialize(collection.find(Filters.eq("_id", id)).first()?.toJson(), controller.classType)
    }

    override fun delete(id: String) {
        collection.deleteOne(Filters.eq("_id", id))
    }

    /**
     * @return [List<T>] A list of all the objects in the repository.
     */
    override fun findAll(): List<T> {
        return collection.find().map { serializer.deserialize(it.toJson(), controller.classType)!! }.toList()
    }

    override fun save(t: T, id: String): T {
        collection.updateOne(Filters.eq("_id", id), Document("\$set",  Document.parse(serializer.serialize(t))), UpdateOptions().upsert(true))
        return t
    }
}