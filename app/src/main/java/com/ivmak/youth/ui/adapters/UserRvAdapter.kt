package com.ivmak.youth.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivmak.youth.core.model.User
import com.ivmak.youth.databinding.RvUserViewBinding

class UserRvAdapter constructor(private val listener: OnUserClickListener) : ListAdapter<User, UserRvAdapter.ViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RvUserViewBinding = RvUserViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    interface OnUserClickListener {
        fun onUserClicked(user: User)
    }


    class ViewHolder constructor(private val binding: RvUserViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, listener: OnUserClickListener) {
            binding.root.setOnClickListener { _ -> listener.onUserClicked(user)}
            binding.userName.text = user.name
        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.name == newItem.name
    }
}