package com.pszandi.contactlist.features.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pszandi.contactlist.data.Contact
import com.pszandi.contactlist.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val results = 30
    var contactList = MutableLiveData<List<Contact>>()

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers(results)
            contactList.postValue(response.body()?.results)
        }
    }
}


