package com.example.app2.data

import java.io.Serializable

data class User(
    val name: Name,
    val phoneNumber: String,
    val image: String
) : Serializable