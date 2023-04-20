package br.edu.infnet.android.teste3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.android.teste3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}