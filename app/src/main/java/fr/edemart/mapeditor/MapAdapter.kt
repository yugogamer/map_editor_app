package fr.edemart.mapeditor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class MapAdapter(context: Context, maps: MutableList<String>?) : ArrayAdapter<String>(context, 0,
    maps as MutableList<String>
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val mapView = if (convertView is MapView) {
            convertView
        } else {
            MapView(context)
        }

        mapView.mapList(getItem(position))
        return mapView
    }
}