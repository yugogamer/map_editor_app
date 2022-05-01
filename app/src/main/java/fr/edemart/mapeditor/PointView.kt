package fr.edemart.mapeditor

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import fr.edemart.mapeditor.entity.Point
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

    }
}