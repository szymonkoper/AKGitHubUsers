package com.szymonkoper.akgithubusers.akgithubusers.model.owner

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.owner_list_item.view.*

class OwnerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var owner: Owner? = null
        set(value) {
            field = value
            view.txt_name.text = owner?.login ?: "-"

            Picasso.get()
                .load(owner?.avatarUrl)
                .placeholder(ColorDrawable(Color.BLACK))
                .into(view.iv_avatar);

        }
}
