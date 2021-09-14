package com.ivmak.youth.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivmak.youth.core.model.YouthEvent
import com.ivmak.youth.databinding.RvUserViewBinding
import java.text.SimpleDateFormat
import java.util.*

class EventRvAdapter constructor(
    private val listener: OnEventClickListener,
    private val deleteListener: OnEventDeleteClickListener? = null
) : ListAdapter<YouthEvent, EventRvAdapter.ViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RvUserViewBinding = RvUserViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    interface OnEventClickListener {
        fun onEventClicked(event: YouthEvent)
    }

    interface OnEventDeleteClickListener {
        fun onEventDelete(event: YouthEvent)
    }


    inner class ViewHolder constructor(private val binding: RvUserViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: YouthEvent) {
            if (deleteListener == null) {
                binding.deleteBtn.visibility = View.GONE
            } else {
                binding.deleteBtn.visibility = View.VISIBLE
                binding.deleteBtn.setOnClickListener { deleteListener.onEventDelete(event) }
            }
            binding.root.setOnClickListener { _ -> listener.onEventClicked(event)}
            val formatter = SimpleDateFormat.getDateInstance()
            binding.userName.text = "${event.name} ${formatter.format(Date(event.timestamp))}"
        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<YouthEvent>() {
        override fun areItemsTheSame(oldItem: YouthEvent, newItem: YouthEvent): Boolean =
            oldItem.timestamp == newItem.timestamp

        override fun areContentsTheSame(oldItem: YouthEvent, newItem: YouthEvent): Boolean =
            oldItem.name == newItem.name && oldItem.description == newItem.description && oldItem.members.size == newItem.members.size
    }
}