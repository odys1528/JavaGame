@file:JvmName("ReadFiles")

package com.odys.javagame

import java.io.InputStream
import java.io.Reader

fun readLocationInfo(reader1: InputStream, reader2: InputStream) : Map<Int, Location> {

    val locations = mutableMapOf<Int, Location>()

    reader1.reader().forEachLine {
        val tokens = it.split("`")
        val location = Location(tokens[0].toInt(), tokens[1])
        locations[location.locationID] = location
    }

    reader2.reader().forEachLine {
        val tokens = it.split(",")
        locations[tokens[0].toInt()]?.addExit(tokens[1], tokens[2].toInt())
    }

    return locations

}

fun toplevel() = println("top level")