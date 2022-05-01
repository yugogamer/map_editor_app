package fr.edemart.mapeditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import fr.edemart.mapeditor.adapter.MapAdapter
import fr.edemart.mapeditor.databinding.ActivityMainBinding
import fr.edemart.mapeditor.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.addingMap.setOnClickListener {
            val editMapIntent = Intent(this, EditMap::class.java)
            editMapIntent.putExtra("name", "")
            this.startActivity(editMapIntent);
        }
        setContentView(binding.root)
        executeCall()
    }
    private fun executeCall() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = ApiClient.apiService.getMapList()
                if (response.isSuccessful && response.body() != null) {
                    var maps = response.body()
                    var listView = findViewById<ListView>(R.id.listview)
                    listView.adapter = MapAdapter(this@MainActivity, maps)

                    binding.progressBar1.visibility = View.GONE
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        executeCall()
        super.onResume()
    }
}