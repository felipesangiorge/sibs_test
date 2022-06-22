/**
 * Created by Adib Faramarzi
 */

@file:JvmName("Lives")
@file:JvmMultifileClass

package com.sibs_test.sibs_test_felipe.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Creates a LiveData that emits the initialValue immediately.
 */
fun <T> liveDataOf(initialValue: T): MutableLiveData<T> {
    return emptyLiveData<T>().apply { value = initialValue }
}


/**
 * Creates a LiveData that emits the value that the `callable` function produces, immediately.
 */
fun <T> liveDataOf(callable: () -> T): LiveData<T> {
    val returnedLiveData = MutableLiveData<T>()
    returnedLiveData.value = callable.invoke()
    return returnedLiveData
}

/**
 * Creates an empty LiveData.
 */
fun <T> emptyLiveData(): MutableLiveData<T> {
    return MutableLiveData()
}