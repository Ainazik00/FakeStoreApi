package com.example.deveem.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.deveem.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController

   //     setupActionBarWithNavController(navController)

//        findViewById<Button>(R.id.img).setOnClickListener {
//            showCategoriesDialog()
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

//    private fun showCategoriesDialog() {
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialog_categories, null)
//        builder.setView(dialogView)
//
//        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)
//
//        val dialog = builder.create()
//
//        dialogView.findViewById<Button>(R.id.btn_decline).setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialogView.findViewById<Button>(R.id.btn_accept).setOnClickListener {
//            val selectedId = radioGroup.checkedRadioButtonId
//            if (selectedId != -1) {
//                val selectedRadioButton = dialogView.findViewById<RadioButton>(selectedId)
//                val selectedCategory = selectedRadioButton.text.toString()
//                Toast.makeText(this, "Selected: $selectedCategory", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show()
//            }
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
}
