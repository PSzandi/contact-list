package com.example.app2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.app2.databinding.ItemUserBinding
import com.example.app2.data.User
import com.example.app2.interfaces.UserClickListener

// Ez lesz az adapter, ami felelős a ViewHolderek létrehozásáért és bindolásáért
// dataSet: az az adat, amit meg akarunk jeleníteni
class UserAdapter(private val dataSet: List<User>, private val itemClickCallback: UserClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Így hozunk létre egy viewHoldert
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // A ViewBinding miatt átalakítjuk:
        /*val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)*/
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Ez adja vissza azt, hogy hány elem van összesen. A layoutManagernek és az adapternek fontos
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // A már létrehozott viewHolderek felbindolása
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // A viewHolder bind metódusát meghívjuk, a datasetnek azzal az elemével, ami a positionben van
        holder.bind(dataSet[position], itemClickCallback)
    }

    // A ViewHolder osztály, ő testesít meg egy RecyclerView elemet, ami megjelenik.
    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        // Totál felesleges lesz a viewBinding miatt
        /*val nameTextView = binding.findViewById<TextView>(R.id.tvName)
        val imageView =  binding.findViewById<ImageView>(R.id.ivAvatar)*/

        // Az aktuális viewHolder name textview-jának beállítjuk a user nevét
        fun bind(user: User, itemClickCallback: UserClickListener) {
            binding.tvName.text = "${user.name.firstName} ${user.name.lastName}"
            binding.ivAvatar.load(user.image)
            binding.root.setOnClickListener {
                itemClickCallback.onUserClicked(user)
            }
        }

    }

}