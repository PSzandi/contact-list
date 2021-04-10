package com.pszandi.contactlist.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pszandi.contactlist.adapter.UserAdapter
import com.pszandi.contactlist.data.Name
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.databinding.ActivityUserListBinding
import com.pszandi.contactlist.interfaces.UserClickListener


class UserListActivity : AppCompatActivity() {

    // layoutManager of the recyclerview
    lateinit var layoutManager: LinearLayoutManager

    // adapter of the recyclerview
    lateinit var userAdapter: UserAdapter

    // View binding:
    private lateinit var binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Létrehozunk egy LinearLayoutManagert, emiatt lesznek sorban az elemek a RV-ban
        layoutManager = LinearLayoutManager(this)
        // Az adapternek átadjuk a user listánkat, ebből fogja bindolni a ViewHoldereket
        userAdapter = UserAdapter(userList, itemClickCallback = object : UserClickListener {
            override fun onUserClicked(user: User) {
                // Itt hozzá fogsz tudni a userhez
                navigateToUserDetailsActivity(user)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        // RV inicializálása: beállítjuk neki az adaptert és a layoutManagert
        // Ez nem kell a ViewBinding miatt már...
        //val recyclerView = this.findViewById<RecyclerView>(R.id.rvUsers)

        // Ezt is átírjuk:
        /*recyclerView.layoutManager = layoutManager
        recyclerView.adapter = userAdapter*/

        // Ezzel tudunk listaelemek közé elválasztást tenni
        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )

        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.adapter = userAdapter

        // Elválasztást megvalósító objektum átadása
        binding.rvUsers.addItemDecoration(dividerItemDecoration)

    }

    private fun navigateToUserDetailsActivity(user: User) {
        // Soha ne példányosíts Activity-t, és ne hívd meg a lifecycle metódusokat kézzel!!!
        // Intent: Szándék. Azt mondjuk a rendszernek, hogy majd ezzel az activityvel akarunk valamit kezdeni.
        val intent = Intent(this, UserDetailsActivity::class.java)
        // Adat átadás activity-k között:
        intent.putExtra(USER, user)
        // Átadjuk az intentet, így a rendszer elindítja a UserDetailsActivity-t
        startActivity(intent)
    }

    // This is the dataset of the recyclerView
    val userList = listOf(
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        ), User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/79.jpg"
        ),
        User(
            Name("Tom", "Holland"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/45.jpg"
        ),
        User(Name("Nikki", "Dem"), "0123456789", "https://randomuser.me/api/portraits/men/28.jpg"),
        User(
            Name("Tony", "Stark"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/91.jpg"
        ),
        User(
            Name("Hanry", "Cavil"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/4.jpg"
        ),
        User(
            Name("Dummy", "User"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/26.jpg"
        ),
        User(
            Name("Alex", "Janssen"),
            "0123456789",
            "https://randomuser.me/api/portraits/men/62.jpg"
        ),
        User(
            Name("Tim", "Howard"),
            "0123456789",
            "https://randomuser.me/api/portraits/women/7.jpg"
        )
    )

    // Ez a javás static-nak felel meg
    companion object {
        // Javában:
        //private final static String USER = "user"

        val USER = "user"
    }

}