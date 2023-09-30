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

class BalanceFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var tIncomeTextView: TextView
    private lateinit var tExpenseTextView: TextView
    private lateinit var balanceTextView: TextView
    private lateinit var editText1: TextView
    private lateinit var logoutButton: Button
    private lateinit var noteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_balance, container, false)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        tIncomeTextView = view.findViewById(R.id.tIncome)
        tExpenseTextView = view.findViewById(R.id.tExpense)
        balanceTextView = view.findViewById(R.id.balance)
        editText1 = view.findViewById(R.id.TextView1)
        logoutButton = view.findViewById(R.id.logout)
         noteButton = view.findViewById(R.id.noteButton)

        val totalExpenseTextView = view.findViewById<TextView>(R.id.tExpense)
        val receivedTotalExpense = arguments?.getFloat("totalExpense", 0.0f) ?: 0.0f


        val editor = sharedPreferences.edit()
        editor.putFloat("receivedTotalExpense", receivedTotalExpense)
        editor.apply()

        tExpenseTextView.text = "Total Expense: $${String.format("%.2f", receivedTotalExpense)}"

        val totalIncomeTextView = view.findViewById<TextView>(R.id.tIncome)
        val receivedTotalIncome = arguments?.getFloat("totalIncome", 0.0f) ?: 0.0f


        editor.putFloat("receivedTotalIncome", receivedTotalIncome)
        editor.apply()

        tIncomeTextView.text = "Total Income: $${String.format("%.2f", receivedTotalIncome)}"

        val balance = receivedTotalIncome - receivedTotalExpense

        balanceTextView.text = "Balance: $${String.format("%.2f", balance)}"




        noteButton.setOnClickListener {
            val intent = Intent(requireContext(),Note_Activity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        logoutButton.setOnClickListener {
            auth.signOut()

            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


        return view
    }
}


