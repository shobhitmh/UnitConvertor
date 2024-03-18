package com.example.unitconvertor

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var spinnerFromUnit: Spinner
    private lateinit var spinnerToUnit: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    private val units = arrayOf("Meters", "Centimeters", "Kilometers") // Add more units as needed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        editTextInput = findViewById(R.id.editTextInput)
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit)
        spinnerToUnit = findViewById(R.id.spinnerToUnit)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromUnit.adapter = adapter
        spinnerToUnit.adapter = adapter

        spinnerFromUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle selection
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        buttonConvert.setOnClickListener {
            convertUnits()
        }
    }

    private fun convertUnits() {
        val inputValue = editTextInput.text.toString().toDouble()
        val fromUnit = spinnerFromUnit.selectedItem as String
        val toUnit = spinnerToUnit.selectedItem as String

        val result = when (fromUnit) {
            "Meters" -> when (toUnit) {
                "Meters" -> inputValue
                "Centimeters" -> inputValue * 100
                "Kilometers" -> inputValue / 1000
                else -> 0.0
            }

            "Centimeters" -> when (toUnit) {
                "Meters" -> inputValue / 100
                "Centimeters" -> inputValue
                "Kilometers" -> inputValue / 100000
                else -> 0.0
            }

            "Kilometers" -> when (toUnit) {
                "Meters" -> inputValue * 1000
                "Centimeters" -> inputValue * 100000
                "Kilometers" -> inputValue
                else -> 0.0
            }

            else -> 0.0
        }

        val resultString = "$inputValue $fromUnit = $result $toUnit"
        textViewResult.text = resultString
    }

}
