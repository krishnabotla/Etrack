package com.example.etrack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class HomepageActivity : AppCompatActivity() {


    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val balanceFragment = BalanceFragment()
        val expenseFragment = expenseFragment()
        val incomeFragment = incomeFragment()



        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, incomeFragment, "profile").hide(incomeFragment)
            add(R.id.fragment_container, expenseFragment, "dashboard").hide(expenseFragment)
            add(R.id.fragment_container, balanceFragment, "home")
        }.commit()

        activeFragment = balanceFragment

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> showFragment(balanceFragment)
                R.id.expense -> showFragment(expenseFragment)
                R.id.income -> showFragment(incomeFragment)
            }
            true
        }


//        val totalExpense = intent.getFloatExtra("totalExpense", 0.0f)
//
//
//
//        val bundle = Bundle()
//        bundle.putFloat("totalExpense", totalExpense)
//        balanceFragment.arguments = bundle
//
//
//
//        val totalIncome = intent.getFloatExtra("totalIncome", 0.0f)
//
//
//
//        val Bundle = Bundle()
//        Bundle.putFloat("totalIncome", totalIncome)
//        balanceFragment.arguments = Bundle

        val totalExpense = intent.getFloatExtra("totalExpense", 0.0f)
        val totalIncome = intent.getFloatExtra("totalIncome", 0.0f)

        val bundle = Bundle()
        bundle.putFloat("totalExpense", totalExpense)
        bundle.putFloat("totalIncome", totalIncome)

        balanceFragment.arguments = bundle




    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }


}
