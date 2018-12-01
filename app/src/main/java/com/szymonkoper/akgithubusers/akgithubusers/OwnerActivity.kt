package com.szymonkoper.akgithubusers.akgithubusers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.szymonkoper.akgithubusers.akgithubusers.ui.owner.OwnerFragment

class OwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.owner_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OwnerFragment.newInstance())
                .commitNow()
        }
    }

}
