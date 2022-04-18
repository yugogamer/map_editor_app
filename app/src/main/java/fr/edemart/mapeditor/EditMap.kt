package fr.edemart.mapeditor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.edemart.mapeditor.databinding.EditMapActivityBinding

class EditMap : AppCompatActivity() {
    private lateinit var binding : EditMapActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditMapActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}