package com.pszandi.contactlist.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.FragmentUserDetailsBinding


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    var user: User? = null
    //var phoneIcon : String = "https://www.pngegg.com/en/png-xixwv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kiszedjük az intent-ből a user-t, amit az előző activity-ről adtunk át.
        //user = intent.extras?.getSerializable(UserListFragment.USER) as? User?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.cardImage.load(user?.image)
        binding.userName.text = "${user?.name?.firstName} ${user?.name?.lastName}"
        binding.tvDetails.text = "${user?.phoneNumber}"

    }

}