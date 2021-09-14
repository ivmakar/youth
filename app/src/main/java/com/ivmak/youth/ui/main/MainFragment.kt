package com.ivmak.youth.ui.main

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
import com.ivmak.youth.core.model.YouthEvent
import com.ivmak.youth.databinding.MainFragmentBinding
import com.ivmak.youth.ui.adapters.EventRvAdapter

class MainFragment : Fragment(), EventRvAdapter.OnEventClickListener {

    private lateinit var binding: MainFragmentBinding

    private lateinit var rvAdapter: EventRvAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = (activity as MainActivity).getViewModel(MainViewModel::class.java)

        binding.addUsersBtn.setOnClickListener {Navigation.findNavController(it).navigate(R.id.usersFragment)}

        binding.addEventBtn.setOnClickListener {
            val event = YouthEvent(System.currentTimeMillis(), "Молодежное", "", arrayListOf())
            viewModel.saveEvent(event)
            val bundle = Bundle()
            bundle.putLong(Const.EVENT_TIMESTAMP, event.timestamp)
            Navigation.findNavController(it).navigate(R.id.eventFragment, bundle)
        }

        rvAdapter = EventRvAdapter(this)
        binding.eventsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        viewModel.getEvents().observe(viewLifecycleOwner) {events -> rvAdapter.submitList(events)}

        return binding.root
    }

    override fun onEventClicked(event: YouthEvent) {
        val bundle = Bundle()
        bundle.putLong(Const.EVENT_TIMESTAMP, event.timestamp)
        Navigation.findNavController(binding.root).navigate(R.id.eventFragment, bundle)
    }

}