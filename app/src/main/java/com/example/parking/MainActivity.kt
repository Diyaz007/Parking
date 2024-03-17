package com.example.parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parking.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonFindCar.setOnClickListener {
            val numberCar : String = binding.EditTextCarNumber.text.toString()
            if  (numberCar.isNotEmpty()){
                readData(numberCar)
            }else{
                Toast.makeText(this,"Введите госномер",Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonRegisCar.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadActivity::class.java)
            startActivity(intent)
        }
        binding.buttonDeleteCar.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
        }
    }
    private fun readData(numberCar: String) {
        database = FirebaseDatabase.getInstance().getReference("Cars")
        database.child(numberCar).get().addOnSuccessListener {
            if (it.exists()){
                val number = it.child("carNumber").value
                val fio = it.child("ownerFIO").value
                val model = it.child("carModel").value
                Toast.makeText(this,"Результаты найдены",Toast.LENGTH_SHORT).show()
                binding.EditTextCarNumber.text.clear()
                binding.readName.text = number.toString()
                binding.readOperator.text = fio.toString()
                binding.readLocation.text = model.toString()
            }else{
                Toast.makeText(this,"Номер автомобиля не существует",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Что-то пошло не так",Toast.LENGTH_SHORT).show()
        }
    }

}