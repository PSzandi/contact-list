package com.pszandi.contactlist.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
) : Serializable
