package com.sibs_test.sibs_test_felipe.extensions

import android.os.Handler
import java.util.concurrent.Executor

fun Handler.toExecutor(): Executor = Executor {
    this.post(it)
}