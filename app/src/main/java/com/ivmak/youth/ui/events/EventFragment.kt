package com.ivmak.youth.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivmak.youth.MainActivity
import com.ivmak.youth.R
import com.ivmak.youth.core.Const
import com.ivmak.youth.core.model.User
import com.ivmak.youth.core.model.YouthEvent
import com.ivmak.youth.databinding.FragmentEventBinding
import com.ivmak.youth.ui.adapters.UserRvAdapter
import com.ivmak.youth.ui.main.MainViewModel


class EventFragment : Fragment() {
    private var eventTimestamp: Long? = null

    private lateinit var binding: FragmentEventBinding

    private lateinit var viewModel: MainViewModel
    private var event: YouthEvent? = null

    private lateinit var membersAdapter: UserRvAdapter
    private lateinit var usersAdapter: UserRvAdapter
    private var users = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        viewModel = (activity as MainActivity).getViewModel(MainViewModel::class.java)

        arguments?.let {
            eventTimestamp = it.getLong(Const.EVENT_TIMESTAMP)
        }

        eventTimestamp?.let {
            viewModel.getEventByTs(it)
        }

        viewModel.curEvent.observe(viewLifecycleOwner) {e ->
            run {
                event = e
                updateEventInfo()
            }
        }

        viewModel.users.observe(viewLifecycleOwner) { u ->
            users = u.toMutableList()
        }


        binding.deleteEventBtn.setOnClickListener {
            event?.let { it1 -> viewModel.deleteEvent(it1) }
            Navigation.findNavController(it).popBackStack()
        }


        membersAdapter = UserRvAdapter(object : UserRvAdapter.OnUserClickListener {
            override fun onUserClicked(user: User) {
                event?.let {
                    it.members.remove(user)
                    viewModel.saveEvent(it)
                    eventTimestamp?.let { it1 -> viewModel.getEventByTs(it1) }
                }
            }
        })
        binding.membersRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = membersAdapter
        }


        usersAdapter = UserRvAdapter(object : UserRvAdapter.OnUserClickListener {
            override fun onUserClicked(user: User) {
                event?.let {
                    if (!it.members.contains(user))
                        it.members.add(user)
                    viewModel.saveEvent(it)
                    eventTimestamp?.let { it1 -> viewModel.getEventByTs(it1) }
                }
            }
        })
        binding.allUsersRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        return binding.root
    }

    private fun updateEventInfo() {
        if (event != null) {
            binding.eventName.setText(event!!.name)
            binding.eventDescription.setText(event!!.description)
            membersAdapter.submitList(event!!.members.map { it })
            usersAdapter.submitList(users.filter { !event!!.members.contains(it) })
        }
    }
}