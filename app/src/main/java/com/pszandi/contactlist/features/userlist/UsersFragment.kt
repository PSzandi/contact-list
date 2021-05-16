package com.pszandi.contactlist.features.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pszandi.contactlist.R
import com.pszandi.contactlist.adapter.UserAdapter
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.FragmentUserListBinding
import com.pszandi.contactlist.interfaces.UserClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : Fragment() {

    // adapter of the recyclerview
    lateinit var userAdapter: UserAdapter

    // View binding:
    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Az adapternek átadjuk a user listánkat, ebből fogja bindolni a ViewHoldereket
        userAdapter = UserAdapter(arrayListOf(), itemClickCallback = object : UserClickListener {
            override fun onUserClicked(user: User) {
                // Itt hozzá fogsz tudni a userhez
                navigateToUserDetailsFragment(user)
            }
        })
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.userList.observe(this, { userList ->
            userAdapter.updateData(userList)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getUsers()
        }
    }

    override fun onStart() {
        super.onStart()
        // Ezzel tudunk listaelemek közé elválasztást tenni
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayout.HORIZONTAL
        )
        // Elválasztást megvalósító objektum átadása
        binding.rvUsers.addItemDecoration(dividerItemDecoration)

    }

    private fun navigateToUserDetailsFragment(user: User) {
        Navigation.findNavController(activity as FragmentActivity, R.id.fragmentContainer)
            .navigate(UsersFragmentDirections.actionUserListFragmentToUserDetailsFragment(user))
    }

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"
        val USER = "user"
    }

}