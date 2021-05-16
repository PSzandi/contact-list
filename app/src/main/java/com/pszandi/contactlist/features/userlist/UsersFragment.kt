package com.pszandi.contactlist.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import okhttp3.Dispatcher
import retrofit2.HttpException


class UsersFragment : Fragment() {

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
        userAdapter = UserAdapter(arrayListOf(), itemClickCallback = object : UserClickListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userService = RetrofitFactory.makeUserService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = userService.getUsers(10)
            withContext(Dispatchers.Main){
                try {
                    // If the webservice call was successful
                    if(response.isSuccessful()){
                        //todo refresh user list in recyclerView
                    } else {
                        Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                } catch(ex: HttpException){
                        Toast.makeText(context,"Exception ${ex.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
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

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"
        val USER = "user"
    }

}