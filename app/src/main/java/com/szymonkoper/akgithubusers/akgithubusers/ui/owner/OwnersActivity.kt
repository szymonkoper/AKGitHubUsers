package com.szymonkoper.akgithubusers.akgithubusers.ui.owner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.szymonkoper.akgithubusers.akgithubusers.R

class OwnersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.owners_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OwnerFragment.newInstance())
                .commitNow()
        }
    }

}
