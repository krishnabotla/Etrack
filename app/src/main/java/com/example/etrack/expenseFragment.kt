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
import com.example.etrack.R

class expenseFragment : Fragment() {
    private lateinit var foodEditText: EditText
    private lateinit var groceryEditText: EditText
    private lateinit var travelEditText: EditText
    private lateinit var othersEditText: EditText
    private lateinit var totalExpenseTextView: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense, container, false)

        // Initialize UI elements
        foodEditText = view.findViewById(R.id.food)
        groceryEditText = view.findViewById(R.id.grocery)
        travelEditText = view.findViewById(R.id.travel)
        othersEditText = view.findViewById(R.id.others)
        totalExpenseTextView = view.findViewById(R.id.total_expense)

        val calculateButton: Button = view.findViewById(R.id.calculate_button)


        sharedPreferences = requireActivity().getSharedPreferences("ExpenseData", Context.MODE_PRIVATE)

        // Load previously saved values
        val savedFoodExpense = sharedPreferences.getFloat("foodExpense", 0.0f)
        val savedGroceryExpense = sharedPreferences.getFloat("groceryExpense", 0.0f)
        val savedTravelExpense = sharedPreferences.getFloat("travelExpense", 0.0f)
        val savedOthersExpense = sharedPreferences.getFloat("othersExpense", 0.0f)

//        val savedtotalExpense = sharedPreferences.getFloat("total expense" , 0.0f)
        foodEditText.setText(savedFoodExpense.toString())
        groceryEditText.setText(savedGroceryExpense.toString())
        travelEditText.setText(savedTravelExpense.toString())
        othersEditText.setText(savedOthersExpense.toString())
//        totalExpenseTextView.setText(savedtotalExpense.toString())


        calculateButton.setOnClickListener {
            // Get values from EditText fields and convert them to doubles
            val foodExpense = foodEditText.text.toString().toDoubleOrNull() ?: 0.0
            val groceryExpense = groceryEditText.text.toString().toDoubleOrNull() ?: 0.0
            val travelExpense = travelEditText.text.toString().toDoubleOrNull() ?: 0.0
            val othersExpense = othersEditText.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate the total expense
            val totalExpense = foodExpense + groceryExpense + travelExpense + othersExpense

            // Display the total expense in the TextView
            totalExpenseTextView.text = "Total expense: $${String.format("%.2f", totalExpense)}"

//            val homepageActivity = activity as? HomepageActivity
//            homepageActivity?.setTotalExpense(totalExpense)




            val totalExpenseIntent = Intent(activity, HomepageActivity::class.java)
            totalExpenseIntent.putExtra("totalExpense", totalExpense.toFloat())
            activity?.startActivity(totalExpenseIntent)




            val editor = sharedPreferences.edit()
            editor.putFloat("foodExpense", foodExpense.toFloat())
            editor.putFloat("groceryExpense", groceryExpense.toFloat())
            editor.putFloat("travelExpense", travelExpense.toFloat())
            editor.putFloat("othersExpense", othersExpense.toFloat())
            editor.putFloat("totalExpense", totalExpense.toFloat())
            editor.apply()
        }

        return view
    }
}

