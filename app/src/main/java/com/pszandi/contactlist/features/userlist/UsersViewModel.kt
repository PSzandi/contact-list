package com.pszandi.contactlist.features.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pszandi.contactlist.data.User
import com.pszandi.contactlist.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val results = 30
    var userList = MutableLiveData<List<User>>()

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers(results)
            userList.postValue(response.body()?.results)
        }
    }
}


