package com.pszandi.contactlist.features.contact_details

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.pszandi.contactlist.activities.MainActivity
import com.pszandi.contactlist.data.Contact
import com.pszandi.contactlist.databinding.FragmentContactDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

const val MY_PERMISSIONS_REQUEST_CALL_PHONE = 123

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
    ): View {
        binding = FragmentContactDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardImage.load(contact?.picture?.large)
        binding.userName.text = "${contact?.name?.firstName} ${contact?.name?.lastName}"
        binding.tvDetails.text = "${contact?.phoneNumber}"
        binding.cardWidget.setOnClickListener {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity as Activity, arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE
                )

                // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            } else {
                callContact()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            callContact()
        }
    }

    private fun callContact() {
        //You already have permission
        try {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact?.phoneNumber))
            (activity as MainActivity).startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}