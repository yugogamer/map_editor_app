package fr.edemart.mapeditor

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView

class MapView(context: Context) : FrameLayout(context) {
    init {
        inflate(context, R.layout.view_map, this)
    }
    fun mapList(map: String?) {
        findViewById<TextView>(R.id.name).setText(map)
    }
}