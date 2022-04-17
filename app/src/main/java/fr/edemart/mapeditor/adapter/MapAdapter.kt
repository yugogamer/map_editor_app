package fr.edemart.mapeditor.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import fr.edemart.mapeditor.MapView
import fr.edemart.mapeditor.R

class MapAdapter(context: Context, maps: MutableList<String>?) : ArrayAdapter<String>(context, 0,
    maps as MutableList<String>
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val mapView = if (convertView is MapView) {
            convertView
        } else {
            MapView(context)
        }

        val editMap = mapView.findViewById<Button>(R.id.edit_map_butt)

        val deleteMap = mapView.findViewById<Button>(R.id.del_map_butt)

        editMap.setOnClickListener{
            println("click edit map")
            getItem(position)?.let {
                mapView.editMap(it, context)
            }
        }

        deleteMap.setOnClickListener{
            println("click edit map")
            getItem(position)?.let {
                println("enter in delete")
                //mapView.deleteMapAction(it)
            }
        }

        deleteMap.setOnClickListener{
        }

        mapView.populate(getItem(position))
        return mapView
    }
}