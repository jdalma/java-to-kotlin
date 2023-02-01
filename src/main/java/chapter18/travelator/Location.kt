package chapter18.travelator

import chapter18.travelator.geo.Position

data class Location(
    val id: Id<Location>,
    val localName: String,
    val userReadableName: String,
    val position: Position
)