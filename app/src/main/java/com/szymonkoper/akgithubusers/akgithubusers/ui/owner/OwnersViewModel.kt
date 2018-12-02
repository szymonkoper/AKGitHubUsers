package com.szymonkoper.akgithubusers.akgithubusers.ui.owner

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.szymonkoper.akgithubusers.akgithubusers.api.ApiClient
import com.szymonkoper.akgithubusers.akgithubusers.model.owner.Owner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OwnersViewModel : ViewModel() {
    private lateinit var owners: MutableLiveData<List<Owner>>

    private val apiClient by lazy { ApiClient() }

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getOwners(loginQuery: String): LiveData<List<Owner>> {
        if (!::owners.isInitialized) {
            owners = MutableLiveData()
            loadOwners(loginQuery)
        }

        return owners
    }

    fun loadOwners(loginQuery: String) {
        uiScope.launch {
            owners.value = apiClient.searchOwners(loginQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
