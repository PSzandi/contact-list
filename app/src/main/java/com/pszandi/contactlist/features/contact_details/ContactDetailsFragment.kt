package com.pszandi.contactlist.features.contact_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.pszandi.contactlist.data.Contact
import com.pszandi.contactlist.databinding.FragmentContactDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding
    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kiszedjük az bundle-ből a user-t, amit az előző fragmentről adtunk át.
        arguments?.let { args ->
            contact = ContactDetailsFragmentArgs.fromBundle(args).user
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.cardImage.load(contact?.picture?.large)
        binding.userName.text = "${contact?.name?.firstName} ${contact?.name?.lastName}"
        binding.tvDetails.text = "${contact?.phoneNumber}"

    }

}