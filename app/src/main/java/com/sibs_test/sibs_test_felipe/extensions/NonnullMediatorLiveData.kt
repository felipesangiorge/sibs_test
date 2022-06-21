/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sibs_test.sibs_test_felipe.extensions

import android.os.Looper
import androidx.lifecycle.MediatorLiveData

/**
 * A LiveData class that is not nullable.
 */
@Suppress("RedundantOverride") // We actually need this to force value to be NonNull
class NonnullMediatorLiveData<T : Any>(
    private val data: T
) : MediatorLiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        if (Looper.myLooper() == Looper.getMainLooper()) {
            value = data
        } else {
            postValue(data)
        }
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }

    override fun getValue(): T {
        return super.getValue() ?: data
    }
}
