package com.pszandi.contactlist.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contact(
    val name: Name,
    @SerializedName("phone")
    val phoneNumber: String,
    val picture: ProfilePicture
) : Serializable