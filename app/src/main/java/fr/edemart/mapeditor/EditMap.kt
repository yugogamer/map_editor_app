package fr.edemart.mapeditor

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import fr.edemart.mapeditor.adapter.MapAdapter
import fr.edemart.mapeditor.adapter.PointAdapter
import fr.edemart.mapeditor.databinding.EditMapActivityBinding
import fr.edemart.mapeditor.entity.Point
import fr.edemart.mapeditor.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditMap : AppCompatActivity() {
    private lateinit var binding : EditMapActivityBinding
    private lateinit var mapSavedname : String
    private lateinit var points: MutableList<Point>
    private lateinit var adapter: PointAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var name: String = (intent.extras?.get("name") ?: "") as String
        binding = EditMapActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapName = findViewById<EditText>(R.id.edit_name_map)
        mapName.setText(name)
        mapSavedname = name

        mapName.addTextChangedListener {
            mapSavedname = mapName.text.toString()
        }

        binding.addPointButt.setOnClickListener {
            val point = Point(name = "new")
            this.points.add(point)
            adapter.notifyDataSetChanged()
        }

        binding.savePointButt.setOnClickListener {
            saveMap()
        }


        if (name != ""){
            executeCall(name);
        }else {
            this@EditMap.points = mutableListOf()
            var listView = findViewById<ListView>(R.id.listview)

            this@EditMap.adapter = PointAdapter(this@EditMap, this@EditMap.points)
            listView.adapter = this@EditMap.adapter

            binding.progressBar1.visibility = View.GONE
        }
    }

    private fun saveMap(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = ApiClient.apiService.createMap(this@EditMap.mapSavedname, this@EditMap.points)
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@EditMap,
                        R.string.saved_succes,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@EditMap,
                        "Error: ${response.body()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@EditMap,
                    "Error: ${e}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun executeCall(name: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = ApiClient.apiService.getMapById(name)
                if (response.isSuccessful && response.body() != null) {
                    var points = response.body()
                    this@EditMap.points = points as MutableList<Point>
                    var listView = findViewById<ListView>(R.id.listview)

                    this@EditMap.adapter = PointAdapter(this@EditMap, this@EditMap.points)
                    listView.adapter = this@EditMap.adapter

                    binding.progressBar1.visibility = View.GONE
                } else {
                    Toast.makeText(
                        this@EditMap,
                        "No usable content",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@EditMap,
                    "Not usable content",
                    Toast.LENGTH_LONG
                ).show()
                this@EditMap.points = mutableListOf()
                var listView = findViewById<ListView>(R.id.listview)

                this@EditMap.adapter = PointAdapter(this@EditMap, this@EditMap.points)
                listView.adapter = this@EditMap.adapter

                binding.progressBar1.visibility = View.GONE
            }
        }
    }
}