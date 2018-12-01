package com.szymonkoper.akgithubusers.akgithubusers.ui.owner

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szymonkoper.akgithubusers.akgithubusers.OwnerViewHolder
import com.szymonkoper.akgithubusers.akgithubusers.R
import com.szymonkoper.akgithubusers.akgithubusers.model.Owner
import kotlinx.android.synthetic.main.owner_fragment.*

class OwnerAdapter(val onClick: (Owner) -> Unit) : RecyclerView.Adapter<OwnerViewHolder>() {
    var owners: List<Owner> = emptyList()

    override fun getItemCount() = owners.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.owner_list_item, parent, false)
        return OwnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val owner = owners[position]
        holder.owner = owner
        holder.view.setOnClickListener { onClick(owner) }
    }
}

class OwnerFragment : Fragment() {
    companion object {
        fun newInstance() = OwnerFragment()
    }

    private lateinit var viewModel: OwnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.owner_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(OwnerViewModel::class.java)

        rv_owners.layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }

        val adapter = OwnerAdapter { owner -> onItemClicked(owner)}
        rv_owners.adapter = adapter
        viewModel.getOwners().observe(this, Observer {
            adapter.owners = it ?: emptyList()
            adapter.notifyDataSetChanged()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OwnerViewModel::class.java)
        val owners = viewModel.getOwners()
        print(owners)
    }

    private fun onItemClicked(owner: Owner) {
        println("clicked on owner: ${owner.name}")
    }
}
