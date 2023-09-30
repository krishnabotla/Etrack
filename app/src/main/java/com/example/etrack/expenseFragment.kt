package com.example.etrack

//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import com.example.etrack.R
//
//class expenseFragment : Fragment() {
//    private lateinit var foodEditText: EditText
//    private lateinit var groceryEditText: EditText
//    private lateinit var travelEditText: EditText
//    private lateinit var othersEditText: EditText
//    private lateinit var totalExpenseTextView: TextView
//
//    private lateinit var sharedPreferences: SharedPreferences
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_expense, container, false)
//
//
//        foodEditText = view.findViewById(R.id.food)
//        groceryEditText = view.findViewById(R.id.grocery)
//        travelEditText = view.findViewById(R.id.travel)
//        othersEditText = view.findViewById(R.id.others)
//        totalExpenseTextView = view.findViewById(R.id.total_expense)
//
//        val calculateButton: Button = view.findViewById(R.id.calculate_button)
//
//
//        sharedPreferences = requireActivity().getSharedPreferences("ExpenseData", Context.MODE_PRIVATE)
//
//
//        val savedFoodExpense = sharedPreferences.getFloat("foodExpense", 0.0f)
//        val savedGroceryExpense = sharedPreferences.getFloat("groceryExpense", 0.0f)
//        val savedTravelExpense = sharedPreferences.getFloat("travelExpense", 0.0f)
//        val savedOthersExpense = sharedPreferences.getFloat("othersExpense", 0.0f)
//
//
//        foodEditText.setText(savedFoodExpense.toString())
//        groceryEditText.setText(savedGroceryExpense.toString())
//        travelEditText.setText(savedTravelExpense.toString())
//        othersEditText.setText(savedOthersExpense.toString())
//
//
//
//        calculateButton.setOnClickListener {
//
//            val foodExpense = foodEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val groceryExpense = groceryEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val travelExpense = travelEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val othersExpense = othersEditText.text.toString().toDoubleOrNull() ?: 0.0
//
//
//            val totalExpense = foodExpense + groceryExpense + travelExpense + othersExpense
//
//            // Display the total expense in the TextView
//            totalExpenseTextView.text = "Total expense: $${String.format("%.2f", totalExpense)}"
//
//            val homepageActivity = activity as? HomepageActivity
//            homepageActivity?.setTotalExpense(totalExpense)
//
//
//
//
//
//
//
//
//            val editor = sharedPreferences.edit()
//            editor.putFloat("foodExpense", foodExpense.toFloat())
//            editor.putFloat("groceryExpense", groceryExpense.toFloat())
//            editor.putFloat("travelExpense", travelExpense.toFloat())
//            editor.putFloat("othersExpense", othersExpense.toFloat())
//            editor.putFloat("totalExpense", totalExpense.toFloat())
//            editor.apply()
//        }
//
//        return view
//    }
//}

//
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import com.example.etrack.R
//
//class expenseFragment : Fragment() {
//    private lateinit var foodEditText: EditText
//    private lateinit var groceryEditText: EditText
//    private lateinit var travelEditText: EditText
//    private lateinit var othersEditText: EditText
//    private lateinit var totalExpenseTextView: TextView
//
//    private lateinit var sharedPreferences: SharedPreferences
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_expense, container, false)
//
//        // Initialize UI elements
//        foodEditText = view.findViewById(R.id.food)
//        groceryEditText = view.findViewById(R.id.grocery)
//        travelEditText = view.findViewById(R.id.travel)
//        othersEditText = view.findViewById(R.id.others)
//        totalExpenseTextView = view.findViewById(R.id.total_expense)
//
//        val calculateButton: Button = view.findViewById(R.id.calculate_button)
//
//        // Initialize SharedPreferences
//        sharedPreferences = requireActivity().getSharedPreferences("ExpenseData", Context.MODE_PRIVATE)
//
//        // Load saved expenses from SharedPreferences
//        val savedFoodExpense = sharedPreferences.getFloat("foodExpense", 0.0f)
//        val savedGroceryExpense = sharedPreferences.getFloat("groceryExpense", 0.0f)
//        val savedTravelExpense = sharedPreferences.getFloat("travelExpense", 0.0f)
//        val savedOthersExpense = sharedPreferences.getFloat("othersExpense", 0.0f)
//
//        // Set the EditText fields to saved values
//        foodEditText.setText(savedFoodExpense.toString())
//        groceryEditText.setText(savedGroceryExpense.toString())
//        travelEditText.setText(savedTravelExpense.toString())
//        othersEditText.setText(savedOthersExpense.toString())
//
//        calculateButton.setOnClickListener {
//            // Parse user input into double values, defaulting to 0.0 if parsing fails
//            val foodExpense = foodEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val groceryExpense = groceryEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val travelExpense = travelEditText.text.toString().toDoubleOrNull() ?: 0.0
//            val othersExpense = othersEditText.text.toString().toDoubleOrNull() ?: 0.0
//
//            // Calculate total expense
//            val totalExpense = foodExpense + groceryExpense + travelExpense + othersExpense
//
////            // Display the total expense in the TextView
////            totalExpenseTextView.text = getString(R.string.total_expense_format, totalExpense)
////
////            // Cast the activity to HomepageActivity (if it's the host)
////            val homepageActivity = activity as? HomepageActivity
////            homepageActivity?.setTotalExpense(totalExpense)
//
//            // Create a Bundle to pass data to the fragment
//            val bundle = Bundle()
//            bundle.putFloat("totalExpense", totalExpense.toFloat())
//
//            // Create an instance of the BalanceFragment
//            val balanceFragment = BalanceFragment()
//            balanceFragment.arguments = bundle
//
//            // Replace the current fragment with the BalanceFragment
//            val fragmentManager = requireActivity().supportFragmentManager
//            fragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, balanceFragment, "balance")
//                .addToBackStack(null)
//                .commit()
//
//            // Save the expenses to SharedPreferences
//            val editor = sharedPreferences.edit()
//            editor.putFloat("foodExpense", foodExpense.toFloat())
//            editor.putFloat("groceryExpense", groceryExpense.toFloat())
//            editor.putFloat("travelExpense", travelExpense.toFloat())
//            editor.putFloat("othersExpense", othersExpense.toFloat())
//            editor.putFloat("totalExpense", totalExpense.toFloat())
//            editor.apply()
//        }
//
//        return view
//    }
//}


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


        foodEditText = view.findViewById(R.id.food)
        groceryEditText = view.findViewById(R.id.grocery)
        travelEditText = view.findViewById(R.id.travel)
        othersEditText = view.findViewById(R.id.others)
        totalExpenseTextView = view.findViewById(R.id.total_expense)

        val calculateButton: Button = view.findViewById(R.id.calculate_button)


        sharedPreferences = requireActivity().getSharedPreferences("ExpenseData", Context.MODE_PRIVATE)


        val savedFoodExpense = sharedPreferences.getFloat("foodExpense", 0.0f)
        val savedGroceryExpense = sharedPreferences.getFloat("groceryExpense", 0.0f)
        val savedTravelExpense = sharedPreferences.getFloat("travelExpense", 0.0f)
        val savedOthersExpense = sharedPreferences.getFloat("othersExpense", 0.0f)


        foodEditText.setText(savedFoodExpense.toString())
        groceryEditText.setText(savedGroceryExpense.toString())
        travelEditText.setText(savedTravelExpense.toString())
        othersEditText.setText(savedOthersExpense.toString())



        calculateButton.setOnClickListener {

            val foodExpense = foodEditText.text.toString().toDoubleOrNull() ?: 0.0
            val groceryExpense = groceryEditText.text.toString().toDoubleOrNull() ?: 0.0
            val travelExpense = travelEditText.text.toString().toDoubleOrNull() ?: 0.0
            val othersExpense = othersEditText.text.toString().toDoubleOrNull() ?: 0.0


            val totalExpense = foodExpense + groceryExpense + travelExpense + othersExpense


            totalExpenseTextView.text = "Total expense: $${String.format("%.2f", totalExpense)}"






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

