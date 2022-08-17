package com.example.userapptask.data.userData
data class Address(
    val street: String,
    // val suite: String,
    val city: String
){
    override fun toString(): String {
        return "$street:$city"
    }
}
