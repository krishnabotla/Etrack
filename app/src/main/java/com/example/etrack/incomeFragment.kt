package com.example.etrack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class incomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var fulltimeEditText: EditText
    private lateinit var parttimeEditText: EditText
    private lateinit var totalIncomeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var auth = FirebaseAuth.getInstance()
//        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        // Initialize UI elements
        fulltimeEditText = view.findViewById(R.id.fulltime)
        parttimeEditText = view.findViewById(R.id.partime)
        totalIncomeTextView = view.findViewById(R.id.tIncome)

        val calculateButton: Button = view.findViewById(R.id.calculate_button)

        sharedPreferences = requireActivity().getSharedPreferences("IncomeData", Context.MODE_PRIVATE)

        // Load previously saved values
        val savedFullTimeIncome = sharedPreferences.getFloat("fullTimeIncome", 0.0f)
        val savedPartTimeIncome = sharedPreferences.getFloat("partTimeIncome", 0.0f)
        val savedtotalIncome = sharedPreferences.getFloat("Total Income", 0.0f)


        totalIncomeTextView.setText(savedtotalIncome.toString())
        fulltimeEditText.setText(savedFullTimeIncome.toString())
        parttimeEditText.setText(savedPartTimeIncome.toString())

        calculateButton.setOnClickListener {
            // Get values from EditText fields and convert them to doubles
            val fullTimeIncome = fulltimeEditText.text.toString().toDoubleOrNull() ?: 0.0
            val partTimeIncome = parttimeEditText.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate the total income
            val totalIncome = fullTimeIncome + partTimeIncome

            // Display the total income in the TextView
            totalIncomeTextView.text = "Total income: $${String.format("%.2f", totalIncome)}"


            // Pass the total expense value to the parent activity (HomepageActivity)




            val totalIncomeIntent = Intent(activity, HomepageActivity::class.java)
            totalIncomeIntent.putExtra("totalIncome", totalIncome.toFloat())
            activity?.startActivity(totalIncomeIntent)



            val editor = sharedPreferences.edit()
            editor.putFloat("fullTimeIncome", fullTimeIncome.toFloat())
            editor.putFloat("partTimeIncome", partTimeIncome.toFloat())
            editor.apply()


        }

        return view
    }
}

