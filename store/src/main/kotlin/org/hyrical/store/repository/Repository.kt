package org.hyrical.store.repository

import org.hyrical.store.DataStoreController

/**
 * The base repository for all [Storable] objects,
 * initiated by a [DataStoreController]
 *
 * @author Nopox
 * @since 11/10/22
 */
interface Repository<T> {

    /**
     * @param [id] The ID of the [T] object that you are searching for.
     *
     * @return [T?] The [T] object if found else null.
     */
    fun search(id: String): T?

    /**
     * @param [t] The object to save.
     *
     * @return [T] The object saved.
     */
    fun save(t: T, id: String): T

    /**
     * @param [id] The ID of the [T] object to delete.
     */
    fun delete(id: String)


    /**
     * @return [List<T>] A list of all the objects in the repository.
     */
    fun findAll(): List<T>
}