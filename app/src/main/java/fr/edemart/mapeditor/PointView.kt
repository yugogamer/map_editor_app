package fr.edemart.mapeditor

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import fr.edemart.mapeditor.entity.Point
import fr.edemart.mapeditor.entity.Type
import fr.edemart.mapeditor.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class PointView(context: Context) : FrameLayout(context) {
    init {
        inflate(context, R.layout.view_point, this)
    }

    fun populate(point: Point?) {
        if (point != null) {
            if (point.name != null){
                findViewById<TextView>(R.id.name).setText(point.name)
            }else{
                findViewById<TextView>(R.id.name).setText("")
            }

            if (point.longitude != null && point.latitude != null){
                findViewById<TextView>(R.id.cordinate).setText("lont:${point.longitude} lat:${point.latitude}")
            }else{
                findViewById<TextView>(R.id.cordinate).setText("")
            }
        }
    }

    fun editMap(point: Point, context: Context){
        val dialog = Dialog(context)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE) // if you have blue line on top of your dialog, you need use thisfezfzedaz
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_edit_point)
        val editionName = dialog.findViewById<EditText>(R.id.edit_name)
        val editionType = dialog.findViewById<EditText>(R.id.edit_type)
        val editionLong = dialog.findViewById<EditText>(R.id.edit_long)
        val editionLat = dialog.findViewById<EditText>(R.id.edit_lat)
        val saveButt = dialog.findViewById<Button>(R.id.save_butt)
        val cancelButt = dialog.findViewById<Button>(R.id.cancel_butt)

        editionName.setText(point.name)
        editionType.setText(point.type.toString())
        editionLong.setText(point.longitude.toString())
        editionLat.setText(point.latitude.toString())

        saveButt.setOnClickListener {
            point.name = editionName.text.toString()
            try {
                point.type = Type.valueOf(editionType.text.toString())
            }catch (e: Exception){
                point.type = Type.NONE
            }

            try {
                point.latitude = editionLong.text.toString().toFloat()
                point.longitude = editionLat.text.toString().toFloat()
            }catch (e: Exception){
                point.latitude = 0.0f
                point.longitude = 0.0f
            }

            dialog.dismiss()
        }
        cancelButt.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}