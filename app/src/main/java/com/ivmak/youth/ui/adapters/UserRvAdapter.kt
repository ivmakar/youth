package com.ivmak.youth.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivmak.youth.core.model.User
import com.ivmak.youth.databinding.RvUserViewBinding

class UserRvAdapter constructor(
    private val listener: OnUserClickListener,
    private val deleteListener: OnUserDeleteClickListener? = null
) : ListAdapter<User, UserRvAdapter.ViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RvUserViewBinding = RvUserViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    interface OnUserClickListener {
        fun onUserClicked(user: User)
    }

    interface OnUserDeleteClickListener {
        fun onUserDelete(user: User)
    }


    inner class ViewHolder constructor(private val binding: RvUserViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, position: Int) {
            if (deleteListener == null) {
                binding.deleteBtn.visibility = View.GONE
            } else {
                binding.deleteBtn.visibility = View.VISIBLE
                binding.deleteBtn.setOnClickListener { deleteListener.onUserDelete(user) }
            }
            binding.root.setOnClickListener { _ -> listener.onUserClicked(user)}
            binding.userName.text = "${position + 1}. ${user.name}"
        }
    }

    class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            false
    }
}