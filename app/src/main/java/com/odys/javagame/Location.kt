package com.odys.javagame

data class Location(val locationID: Int, val description: String) {

    val exits = mutableMapOf<String, Int>()

    fun addExit(direction: String, destinationID: Int) {
        exits[direction] = destinationID
    }

}