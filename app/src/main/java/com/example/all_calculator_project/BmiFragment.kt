package com.example.all_calculator_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.all_calculator_project.databinding.FragmentBmiBinding

class BmiFragment : Fragment() {

    private lateinit var binding : FragmentBmiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmiBinding.inflate(layoutInflater,container,false)
        val rootView = inflater.inflate(R.layout.fragment_bmi, container, false)

            binding.button.setOnClickListener {
            val weight = binding.weightText.text.toString()
            val height = binding.heightText.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val bmiDigit = String.format("%.2f", bmi).toFloat()

                displayResult(bmiDigit)
            }
        }

        return rootView
    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(requireContext(), "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(requireContext(), "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        binding.resultText.text = bmi.toString()
        binding.rangeText.text = "You are healthy"
        binding.cmCal.text = "(Normal range is 18.5-24.5)"

        var result = ""
        var color = 0
        var range = ""

        when {
            bmi < 18.50 -> {
                result = "Underweight"
                color = R.color.under_weight
                range = "(Underweight range is less than 18.50)"
            }

            bmi in 18.50..24.99 -> {
                result = "Healty"
                color = R.color.normal
                range = "(Healty range is 18.50-24.99)"
            }

            bmi in 25.00..29.99 -> {
                result = "Overweight"
                color = R.color.over_weight
                range = "(Overweight range is 24.99-29.99)"
            }

            bmi > 29.99 -> {
                result = "Obese"
                color = R.color.obese
                range = "(Obese range is greater than 29.99)"
            }
        }
        binding.resultText.setTextColor(ContextCompat.getColor(requireContext(), color))
        binding.resultText.text = result
        binding.rangeText.setTextColor(ContextCompat.getColor(requireContext(), color))
        binding.rangeText.text = range
    }
}