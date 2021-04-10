package com.pszandi.contactlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pszandi.contactlist.data.User

class UserDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // todo csináld meg a layout inflate-elést a viewBindinggal, és add át a setContentView-ban.
        // lesz rá példa a UserListActivity-ben

        // Kiszedjük az intent-ből a user-t, amit az előző activity-ről adtunk át.
        val user: User? = intent.extras?.getSerializable(UserListActivity.USER) as? User?
        // todo itt már van usered, onStartban ezt tudod majd használni.
    }

    override fun onStart() {
        super.onStart()
        // todo ide kell majd a nézet logika
    }

    // Todo készíts egy activity-t, az elküldött kép alapján, a user objektumból építkezve

}