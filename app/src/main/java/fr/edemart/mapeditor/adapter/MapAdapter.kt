package fr.edemart.mapeditor.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import fr.edemart.mapeditor.MapView
import fr.edemart.mapeditor.R
import android.app.AlertDialog
import android.content.Context
import android.widget.Button

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
            getItem(position)?.let {
                mapView.editMap(it, context)
            }
        }

        deleteMap.setOnClickListener{
            getItem(position)?.let { name ->
                val title = context.resources.getString(R.string.dial_title_del_map)
                val message = String.format(context.resources.getString(R.string.dial_message_del_map), name)

                var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialog.setTitle(title)
                alertDialog.setMessage(message)
                alertDialog.setPositiveButton(R.string.dial_pos_del_map) {_,_, ->
                    mapView.deleteMap(name)
                    remove(getItem(position))
                }
                alertDialog.setNegativeButton(R.string.dial_neg_del_map) {_,_, -> }
                alertDialog.create().show()
            }
        }

        mapView.populate(getItem(position))
        return mapView
    }
}