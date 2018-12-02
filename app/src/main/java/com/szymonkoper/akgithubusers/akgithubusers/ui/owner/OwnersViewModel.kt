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

    fun getOwners(): LiveData<List<Owner>> {
        if (!::owners.isInitialized) {
            owners = MutableLiveData()
            loadOwners()
        }

        return owners
    }

    private fun loadOwners() {
        uiScope.launch {
            owners.value = apiClient.searchOwners("szymonk")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
