package com.pszandi.contactlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pszandi.contactlist.R
import com.pszandi.contactlist.adapter.UserAdapter
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.FragmentUserListBinding
import com.pszandi.contactlist.interfaces.UserClickListener
import com.pszandi.contactlist.service.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class UsersFragment : Fragment() {

    // adapter of the recyclerview
    lateinit var userAdapter: UserAdapter

    // View binding:
    private lateinit var binding: FragmentUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Az adapternek átadjuk a user listánkat, ebből fogja bindolni a ViewHoldereket
        userAdapter = UserAdapter(arrayListOf(), itemClickCallback = object : UserClickListener {
            override fun onUserClicked(user: User) {
                // Itt hozzá fogsz tudni a userhez
                navigateToUserDetailsFragment(user)
            }
        })
        getUsers()
    }

    private fun getUsers() {
        val userService = RetrofitFactory.makeUserService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = userService.getUsers(30)
            withContext(Dispatchers.Main) {
                try {
                    // If the webservice call was successful
                    if (response.isSuccessful()) {
                        val userList = response.body()?.results
                        userList?.let {
                            userAdapter.updateData(it)
                        }
                    } else {
                        Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (ex: HttpException) {
                    Toast.makeText(context, "Exception ${ex.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
            .navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user))
    }

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"
        val USER = "user"
    }

}