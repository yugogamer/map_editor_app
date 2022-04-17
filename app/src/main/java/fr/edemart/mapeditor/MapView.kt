package fr.edemart.mapeditor

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import fr.edemart.mapeditor.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MapView(context: Context) : FrameLayout(context) {
    init {
        inflate(context, R.layout.view_map, this)
    }

    fun populate(map: String?) {
        findViewById<TextView>(R.id.name).setText(map)
    }

    fun editMap(name: String, context: Context){
        val editMapIntent = Intent(context, EditMap::class.java)
        editMapIntent.putExtra("name", name)
        context.startActivity(editMapIntent);
    }

    fun deleteMap(name: String){
        GlobalScope.launch {
            try {
                val response = ApiClient.apiService.deleteMap(name)
                if (response.isSuccessful){
                    Toast.makeText(context, R.string.dial_pos_end_del_map, Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context, R.string.dial_neg_end_del_map, Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){

            }
        }
    }
}