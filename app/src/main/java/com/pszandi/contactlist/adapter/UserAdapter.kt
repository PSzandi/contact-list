package com.pszandi.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pszandi.contactlist.databinding.ItemUserBinding
import com.pszandi.contactlist.data.Contact
import com.pszandi.contactlist.interfaces.ContactClickListener

// Ez lesz az adapter, ami felelős a ViewHolderek létrehozásáért és bindolásáért
// dataSet: az az adat, amit meg akarunk jeleníteni
class UserAdapter(private var dataSet: List<Contact>, private val itemClickCallback: ContactClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Így hozunk létre egy viewHoldert
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    fun updateData(list: List<Contact>) {
        dataSet = list
        notifyDataSetChanged()
    }

    // A ViewHolder osztály, ő testesít meg egy RecyclerView elemet, ami megjelenik.
    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact, itemClickCallback: ContactClickListener) {
            binding.tvName.text = "${contact.name.firstName} ${contact.name.lastName}"
            binding.ivAvatar.load(contact.picture.thumbnail)
            binding.root.setOnClickListener {
                itemClickCallback.onContactClicked(contact)
            }
        }

    }

}