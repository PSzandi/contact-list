package com.pszandi.contactlist.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pszandi.contactlist.R
import com.pszandi.contactlist.adapter.UserAdapter
import com.pszandi.contactlist.data.Name
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.FragmentUserListBinding
import com.pszandi.contactlist.interfaces.UserClickListener


class UserListFragment : Fragment() {

    // layoutManager of the recyclerview
    lateinit var layoutManager: LinearLayoutManager

    // adapter of the recyclerview
    lateinit var userAdapter: UserAdapter

    // View binding:
    private lateinit var binding: FragmentUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Létrehozunk egy LinearLayoutManagert, emiatt lesznek sorban az elemek a RV-ban
        layoutManager = LinearLayoutManager(context)
        // Az adapternek átadjuk a user listánkat, ebből fogja bindolni a ViewHoldereket
        userAdapter = UserAdapter(userList, itemClickCallback = object : UserClickListener {
            override fun onUserClicked(user: User) {
                // Itt hozzá fogsz tudni a userhez
                navigateToUserDetailsFragment(user)
            }
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

    override fun onStart() {
        super.onStart()
        // Ezzel tudunk listaelemek közé elválasztást tenni
        val dividerItemDecoration = DividerItemDecoration(
            context,
            layoutManager.orientation
        )

        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.adapter = userAdapter

        // Elválasztást megvalósító objektum átadása
        binding.rvUsers.addItemDecoration(dividerItemDecoration)

    }

    private fun navigateToUserDetailsFragment(user: User) {
        Navigation.findNavController(activity as FragmentActivity, R.id.fragmentContainer)
            .navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user))
    }

    // This is the dataset of the recyclerView
    val userList = listOf(
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ), User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        )
    )

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"

        val USER = "user"
    }

}