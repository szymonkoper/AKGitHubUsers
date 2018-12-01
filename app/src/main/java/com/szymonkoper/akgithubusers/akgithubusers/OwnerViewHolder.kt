package com.szymonkoper.akgithubusers.akgithubusers

import android.support.v7.widget.RecyclerView
import android.view.View
import com.szymonkoper.akgithubusers.akgithubusers.model.Owner
import kotlinx.android.synthetic.main.owner_list_item.view.*

class OwnerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var owner: Owner? = null
    set(value) {
        field = value
        view.txt_name.text = owner?.name
    }
}
