package com.pszandi.contactlist.data

import java.io.Serializable

data class User(
    val name: Name,
    val phoneNumber: String,
    val image: String
) : Serializable