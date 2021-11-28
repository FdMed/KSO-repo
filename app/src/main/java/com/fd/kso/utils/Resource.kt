package com.fd.kso.utils

/**
 * Cette class encapsule les données renvoyés
 * par les services et l'état des réponses
 * @param status prend les valeurs succes/loading/error
 * @param data prend les données retourné par le service
 *              ou bien les données a affiché a l'écran
 * @param message prend un message retourné par le service
 *                 ou bien un message a afficher à l'écran
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
                Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}