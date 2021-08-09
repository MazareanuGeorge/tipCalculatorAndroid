package com.masterandroid.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.masterandroid.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var myButton : Button
    lateinit var EditTextInTextField : EditText
    var selectedId : Int = 0
    var tipPercentage : Double = 1.0
    var roundUp : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        myButton.setOnClickListener { calculateTip() }
    }


    fun calculateTip (){
        selectedId = binding.radioGroupMain.checkedRadioButtonId
        tipPercentage = when(selectedId){
            R.id.twenty_percent -> 0.20
            R.id.fifteen_percent -> 0.15
            R.id.ten_percent -> 0.10
            else -> 1.0
        }
        val stringSum : String
        stringSum = EditTextInTextField.text.toString()
        val cost = stringSum.toDoubleOrNull()
        if(cost == null)
            return
        var tip = tipPercentage * cost
        if(roundUp) {
            var formatTip = kotlin.math.ceil(tip)
            val formattedTip = NumberFormat.getCurrencyInstance().format(formatTip)
            binding.textviewTipAmmountId.text = getString(R.string.tip_ammount_main, formattedTip)
        }
        else
        {
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.textviewTipAmmountId.text = getString(R.string.tip_ammount_main, formattedTip)
        }
    }
    fun initViews(){
        myButton = binding.buttonCalculate
        EditTextInTextField = binding.costOfServiceEditText
        Log.i("Percentage", tipPercentage.toString())
        roundUp = binding.switchRoundTip.isChecked
    }
}