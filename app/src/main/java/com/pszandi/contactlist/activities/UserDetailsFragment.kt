package com.pszandi.contactlist.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.FragmentUserDetailsBinding


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kiszedjük az bundle-ből a user-t, amit az előző fragmentről adtunk át.
        arguments?.let { args ->
            user = UserDetailsFragmentArgs.fromBundle(args).user
        }
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