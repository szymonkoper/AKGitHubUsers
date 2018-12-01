package com.szymonkoper.akgithubusers.akgithubusers.ui.owner

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.szymonkoper.akgithubusers.akgithubusers.model.Owner


class OwnerViewModel : ViewModel() {
    private lateinit var owners: MutableLiveData<List<Owner>>

    fun getOwners(): LiveData<List<Owner>> {
        if (!::owners.isInitialized) {
            owners = MutableLiveData()
            loadOwners()
        }

        return owners
    }

    private fun loadOwners() {
        // TODO: Replace mock with async call
        owners.value = listOf(Owner("Szymcio"), Owner("Dziku"), Owner("Janusz"))
    }
}
