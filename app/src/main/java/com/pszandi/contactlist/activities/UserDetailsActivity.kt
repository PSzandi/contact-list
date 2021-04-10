package com.pszandi.contactlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Kiszedjük az intent-ből a user-t, amit az előző activity-ről adtunk át.
        val user: User? = intent.extras?.getSerializable(UserListActivity.USER) as? User?
        // todo itt már van usered, onStartban ezt tudod majd használni.
    }

    override fun onStart() {
        super.onStart()
        // todo ide kell majd a nézet logika
    }

}