package com.pszandi.contactlist.features.userlist

import android.os.Bundle
import android.view.*
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
import com.pszandi.contactlist.data.Contact
import com.pszandi.contactlist.databinding.FragmentContactListBinding
import com.pszandi.contactlist.interfaces.ContactClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactListFragment : Fragment() {

    // adapter of the recyclerview
    lateinit var userAdapter: UserAdapter

    // View binding:
    private lateinit var binding: FragmentContactListBinding
    private val viewModel: ContactListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Az adapternek átadjuk a user listánkat, ebből fogja bindolni a ViewHoldereket
        userAdapter = UserAdapter(arrayListOf(), itemClickCallback = object : ContactClickListener {
            override fun onContactClicked(contact: Contact) {
                // Itt hozzá fogsz tudni a userhez
                navigateToUserDetailsFragment(contact)
            }
        })
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.contactList.observe(this, { userList ->
            userAdapter.updateData(userList)
            binding.refreshLayout.isRefreshing = false
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.refreshLayout.isRefreshing = true
        binding.refreshLayout.setOnRefreshListener {
            fetchContacts()
        }
        fetchContacts()
        // Ezzel tudunk listaelemek közé elválasztást tenni
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayout.HORIZONTAL
        )
        // Elválasztást megvalósító objektum átadása
        binding.rvUsers.addItemDecoration(dividerItemDecoration)
        setHasOptionsMenu(true)
    }

    private fun fetchContacts() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getUsers()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_contact_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                binding.refreshLayout.isRefreshing = true
                fetchContacts()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun navigateToUserDetailsFragment(contact: Contact) {
        Navigation.findNavController(activity as FragmentActivity, R.id.fragmentContainer)
            .navigate(
                ContactListFragmentDirections.actionUserListFragmentToUserDetailsFragment(
                    contact
                )
            )
    }

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"
        val USER = "user"
    }

}