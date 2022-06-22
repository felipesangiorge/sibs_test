package com.sibs_test.sibs_test_felipe.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sibs_test.sibs_test_felipe.core.PagedListing
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.extensions.liveDataOf
import java.util.concurrent.Executor

object LivePagedListBuilderUtils {

    fun <Key, Value> build(
        initialLoadKey: Key?,
        config: PagedList.Config,
        boundaryCallback: PagedList.BoundaryCallback<Value>?,
        dataSourceFactory: DataSource.Factory<Key, Value>,
        notifyExecutor: Executor,
        fetchExecutor: Executor
    ): LiveData<PagedList<Value>> {

        // We're using reflection because Epoxy requires the modelBuilding executor to be the same as the notifyExecutor, and LivePagedListBuilder does not
        // allow us to set the notifyExecutor
        val method = LivePagedListBuilder::class.java.getDeclaredMethod(
            "create",
            Any::class.java,
            PagedList.Config::class.java,
            PagedList.BoundaryCallback::class.java,
            DataSource.Factory::class.java,
            Executor::class.java,
            Executor::class.java
        ).apply {
            isAccessible = true
        }
        val pagedList = method.invoke(
            null,
            initialLoadKey,
            config,
            boundaryCallback,
            dataSourceFactory,
            notifyExecutor,
            fetchExecutor
        ) as LiveData<PagedList<Value>>

        return pagedList
    }

    fun <T> buildSingleItemPagedListing(
        item: T,
        notifyExecutor: Executor
    ): PagedListing<T> {
        val pagedList = build(
            initialLoadKey = null,
            config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build(),
            boundaryCallback = null,
            dataSourceFactory = SingleItemDataSource.Factory(item),
            notifyExecutor = notifyExecutor,
            fetchExecutor = notifyExecutor
        )

        val refreshState = MutableLiveData<Resource<Unit>>()

        return PagedListing(
            pagedList = pagedList,
            networkState = liveDataOf<Resource<Unit>>(Resource.Success(Unit)),
            retry = {},
            refreshState = refreshState,
            refresh = {
                refreshState.value = Resource.Success(Unit)
            }
        )
    }
}
