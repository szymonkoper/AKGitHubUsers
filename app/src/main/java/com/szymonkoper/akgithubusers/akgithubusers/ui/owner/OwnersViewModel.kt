package com.szymonkoper.akgithubusers.akgithubusers.ui.owner

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.szymonkoper.akgithubusers.akgithubusers.model.owner.Owner


class OwnersViewModel : ViewModel() {
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
        owners.value = MOCKED_OWNERS
    }

    companion object {
        val MOCKED_OWNERS = listOf(
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "szymcio",
                "Szymcio",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "dzikidzik",
                "Dziku",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            ),
            Owner(
                "XjanuszX_pl",
                "Janusz",
                "https://avatars1.githubusercontent.com/u/2790570",
                12
            )
        )
    }
}
