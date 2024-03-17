package com.example.parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parking.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUploadCar.setOnClickListener {
            val numberCar = binding.EditTextCarNumberRegist.text.toString()
            val fio = binding.EditTextFio.text.toString()
            val carModel = binding.EditTextModel.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Cars")
            val car = CarsData(fio,carModel,numberCar)
            database.child(numberCar).setValue(car).addOnSuccessListener {
                binding.EditTextCarNumberRegist.text.clear()
                binding.EditTextFio.text.clear()
                binding.EditTextModel.text.clear()
                Toast.makeText(this,"Сохранено",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Неуспешно",Toast.LENGTH_SHORT).show()
            }
        }
        binding.back.setOnClickListener {
            val intent = Intent(this@UploadActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}