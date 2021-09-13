package com.ivmak.youth.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.ivmak.youth.MainActivity
import com.ivmak.youth.R
import com.ivmak.youth.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

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

        return binding.root
    }

}