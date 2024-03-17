package com.example.parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parking.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonDeleteCar.setOnClickListener {
            val numberCar = binding.EditTextCarNumber2.text.toString()
            if (numberCar.isNotEmpty())
                deleteData(numberCar)
            else
                Toast.makeText(this, "Пожалуйста, введите номер телефона", Toast.LENGTH_SHORT).show()
        }
        binding.back2.setOnClickListener {
            val intent = Intent(this@DeleteActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun deleteData(number: String){
        database = FirebaseDatabase.getInstance().getReference("Cars")
        database.child(number).removeValue().addOnSuccessListener {
            binding.EditTextCarNumber2.text.clear()
            Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Невозможно удалить", Toast.LENGTH_SHORT).show()
        }
    }
}