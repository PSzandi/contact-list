package com.pszandi.contactlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pszandi.contactlist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        supportFragmentManager.fragments.firstOrNull()?.childFragmentManager?.fragments?.firstOrNull()
            ?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}