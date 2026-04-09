package com.example.assesment2_job2

import Note
import NoteViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.assesment2_job2.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var viewModel: NoteViewModel

    private var noteid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //getDatabase function called
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        noteid =intent.getIntExtra("id",-1)
        if (noteid != -1) {

            binding.nameET.setText(intent.getStringExtra("name"))
            binding.addressET.setText(intent.getStringExtra("address"))
            binding.phoneET.setText(intent.getStringExtra("phone"))
            binding.gmailET.setText(intent.getStringExtra("gmail"))



        }

        binding.button.setOnClickListener {
            val name = binding.nameET.text.toString()
            val address = binding.addressET.text.toString()
            val phone = binding.phoneET.text.toString()
            val gmail = binding.gmailET.text.toString()





            if (noteid== -1){
                //insert
                viewModel.insertFromViewModel(
                    Note(name = name, address = address, phone = phone,  gmail = gmail)
                )

            }else{
                //Update
                viewModel.updateFromViewModel(
                    Note(id = noteid, name = name, address = address, phone = phone, gmail = gmail)
                )
            }
            Toast.makeText(this@AddActivity, "data saved successfully", Toast.LENGTH_SHORT).show()
            finish()


        }

    }
}