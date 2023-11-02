package ghar.learn.cognizant.apptddapproach.util

import androidx.recyclerview.widget.DiffUtil
import ghar.learn.cognizant.apptddapproach.db.Art

object Utils {

    const val BASE_URL: String = "https://pixabay.com"
    data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
        companion object {
            fun <T> success(data: T?): Resource<T> {
                return Resource(Status.SUCCESS, data, null)
            }

            fun <T> error(msg: String, data: T?): Resource<T> {
                return Resource(Status.ERROR, data, msg)
            }

            fun <T> loading(data: T?): Resource<T> {
                return Resource(Status.LOADING, data, null)
            }
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
