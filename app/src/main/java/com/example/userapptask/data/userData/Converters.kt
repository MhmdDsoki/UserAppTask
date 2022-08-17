package com.example.userapptask.data.userData

import androidx.room.TypeConverter
class Converters {
    @TypeConverter
    fun addressToString(address:Address) = "$address" //Other options are json string, serialized blob
    @TypeConverter
    fun stringToAddress(value: String):Address{
        val street = value.substringBefore(':')
        val city = value.substringAfter(':')

        return Address(street,city)
    }

//        @TypeConverter
//        fun geoToString(geo: User.Address.Geo) = "$geo"
//        @TypeConverter
//        fun stringToGeo(value: String): User.Address.Geo {
//            val lat = value.substringBefore(':')
//            val lng = value.substringAfter(':')
//
//            return User.Address.Geo(lat,lng)
//        }
}