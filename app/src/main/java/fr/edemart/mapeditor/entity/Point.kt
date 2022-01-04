package fr.edemart.mapeditor.entity

import kotlinx.serialization.*

@Serializable
data class Point(
    var name : String = "",
    val type : Type = Type.NONE,
    var latitude : Float,
    var longitude : Float
)
