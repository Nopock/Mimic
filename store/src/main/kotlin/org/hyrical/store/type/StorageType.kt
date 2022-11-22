package org.hyrical.store.type

import org.hyrical.store.DataStoreController
import org.hyrical.store.repository.AsyncRepository
import org.hyrical.store.repository.Repository
import org.hyrical.store.repository.impl.AsyncMongoRepository
import org.hyrical.store.repository.impl.MongoRepository

/**
 * The type of storage to be used whilst persisting data.
 *
 * @author Nopox
 * @since 11/10/22
 */
enum class StorageType {

    MONGO() {
        override fun <T> build(controller: DataStoreController<T>): Repository<T> {
            return MongoRepository(controller)
        }

        override fun <T> buildAsync(controller: DataStoreController<T>): AsyncRepository<T> {
            return AsyncMongoRepository(controller)
        }
    };

    /**
     * Builds and initiates the [Repository]
     *
     * @param [controller] The owning [DataStoreController]
     */
    abstract fun <T> build(controller: DataStoreController<T>): Repository<T>

    /**
     * Builds and initiates the [AsyncRepository]
     *
     * @param [controller] The owning [DataStoreController]
     */
    abstract fun <T> buildAsync(controller: DataStoreController<T>): AsyncRepository<T>
}