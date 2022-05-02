package fr.edemart.mapeditor.entity

data class Point(
    var name : String = "",
    var type : Type = Type.NONE,
    var latitude : Float = 0.0f,
    var longitude : Float = 0.0f
)
