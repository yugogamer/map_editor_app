package fr.edemart.mapeditor

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import fr.edemart.mapeditor.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MapView(context: Context) : FrameLayout(context) {
    init {
        inflate(context, R.layout.view_map, this)
    }
    fun mapList(map: String?) {
        findViewById<TextView>(R.id.name).setText(map)
        findViewById<Button>(R.id.edit_butt).setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                if (map != null) {
                    val response = ApiClient.apiService.getMapById(map)
                    if (response.isSuccessful && response.body() != null){

                    }
                }
            }
        }
    }
}