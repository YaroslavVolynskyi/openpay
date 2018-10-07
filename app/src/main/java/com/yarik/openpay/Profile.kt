package com.yarik.openpay

data class Profile(val firstName: String,
                   val lastName: String,
                   val location: Location,
                   val avatar: Avatar) {

    data class Location(val country: String,
                        val state: String,
                        val city: String)

    data class Avatar(val imageUrl: String,
                      val width: Int,
                      val height: Int)
}