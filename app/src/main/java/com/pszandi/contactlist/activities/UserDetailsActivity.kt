package com.pszandi.contactlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load

import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.ActivityUserDetailsBinding


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    var user: User? = null
    //var phoneIcon : String = "https://www.pngegg.com/en/png-xixwv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Kiszedjük az intent-ből a user-t, amit az előző activity-ről adtunk át.
        user = intent.extras?.getSerializable(UserListFragment.USER) as? User?
        // todo itt már van usered, onStartban ezt tudod majd használni.

    }

    override fun onStart() {
        super.onStart()
        // todo ide kell majd a nézet logika
        binding.cardImage.load(user?.image)
        binding.userName.text = "${user?.name?.firstName} ${user?.name?.lastName}"
        binding.tvDetails.text = "${user?.phoneNumber}"
       
    }

}