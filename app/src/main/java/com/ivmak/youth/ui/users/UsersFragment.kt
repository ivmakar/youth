package com.ivmak.youth.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivmak.youth.MainActivity
import com.ivmak.youth.R
import com.ivmak.youth.core.model.User
import com.ivmak.youth.databinding.FragmentUsersBinding
import com.ivmak.youth.ui.adapters.UserRvAdapter
import com.ivmak.youth.ui.main.MainFragment
import com.ivmak.youth.ui.main.MainViewModel

class UsersFragment : Fragment(), UserRvAdapter.OnUserClickListener,
    UserRvAdapter.OnUserDeleteClickListener {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentUsersBinding

    private lateinit var rvAdapter: UserRvAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        viewModel = (activity as MainActivity).getViewModel(MainViewModel::class.java)

        rvAdapter = UserRvAdapter(this, this)

        binding.usersRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        viewModel.users.observe(viewLifecycleOwner) {users ->
            rvAdapter.submitList(users)
        }

        binding.addUserBtn.setOnClickListener { saveNewUser() }

        return binding.root
    }

    private fun saveNewUser() {
        if (binding.newUserName.text.isNullOrEmpty()) {
            Toast.makeText(context, "Введите имя", Toast.LENGTH_LONG).show();
        }

        val user = User(binding.newUserName.text.toString())
        viewModel.saveUser(user)
        binding.newUserName.text?.clear()
    }

    override fun onUserClicked(user: User) {

    }

    override fun onUserDelete(user: User) {
        viewModel.deleteUser(user)
    }
}