package fr.edemart.mapeditor.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import fr.edemart.mapeditor.R
import android.app.AlertDialog
import android.content.Context
import android.widget.Button
import fr.edemart.mapeditor.PointView
import fr.edemart.mapeditor.entity.Point

class PointAdapter(context: Context, points: MutableList<Point>?) : ArrayAdapter<Point>(context, 0,
    points as MutableList<Point>
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pointView = if (convertView is PointView) {
            convertView
        } else {
            PointView(context)
        }

        val editPoit = pointView.findViewById<Button>(R.id.edit_point_butt)

        val deletePoint = pointView.findViewById<Button>(R.id.del_point_butt)

        editPoit.setOnClickListener{
            getItem(position)?.let {
                pointView.editMap(it, context)
            }
        }

        deletePoint.setOnClickListener{
            getItem(position)?.let { name ->
                val title = context.resources.getString(R.string.dial_title_del_map)
                val message = context.resources.getString(R.string.dial_message_del_point)

                var alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialog.setTitle(title)
                alertDialog.setMessage(message)
                alertDialog.setPositiveButton(R.string.dial_pos_del_map) {_,_, ->
                    remove(getItem(position))
                }
                alertDialog.setNegativeButton(R.string.dial_neg_del_map) {_,_, -> }
                alertDialog.create().show()
            }
        }

        pointView.populate(getItem(position))
        return pointView
    }
}