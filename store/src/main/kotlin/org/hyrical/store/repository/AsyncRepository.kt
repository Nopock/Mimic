package org.hyrical.store.repository

import java.util.concurrent.CompletableFuture

interface AsyncRepository<T> {

    fun search(id: String): CompletableFuture<T?>

    fun save(t: T, id: String): CompletableFuture<T>

    fun delete(id: String)

    /**
     * @return [List<T>] A list of all the objects in the repository.
     */
    fun findAll(): CompletableFuture<List<T>>
}