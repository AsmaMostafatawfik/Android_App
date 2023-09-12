package com.example.currency_converter_app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : ComponentActivity() {
    lateinit var convertButton: Button
    lateinit var amountFiled:TextInputEditText
    lateinit var resultFiled:TextInputEditText
    lateinit var toDropDownMenu:AutoCompleteTextView
    lateinit var fromDropDownMenu:AutoCompleteTextView
    lateinit var toolbar: Toolbar


    private val egyptianPound="Egyptian Pound"
    private val americanDoller="American Dollar"
    private val AED="AED"
    private val GBP="GBP"

    val values= mapOf(
        americanDoller to 1.0 ,
        egyptianPound to 31.08 ,
        AED to 0.27 ,
        GBP to 1.26
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initilalsViews()
        populateDropDownManu()
        toolbar.inflateMenu(R.menu.options_menu)
        toolbar.setOnMenuItemClickListener{ menuitem->
            if(menuitem.itemId==R.id.share_action){
                val message="${amountFiled.text.toString()} ${fromDropDownMenu.text.toString()} is equal to ${resultFiled.text.toString()}" +
                        "${toDropDownMenu.text.toString()}"
                 val shareIntent= Intent(Intent.ACTION_SEND)
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,message)

                if(shareIntent.resolveActivity(packageManager)!=null)
                {
                    startActivity(shareIntent)
                }
                else{
                    Toast.makeText(this,"no activity found",Toast.LENGTH_SHORT).show()
                }
            }

            true
        }




        amountFiled.addTextChangedListener {
            calculateResult()
        }

        fromDropDownMenu.setOnItemClickListener { parent, view, position, id ->
            calculateResult()
        }
        toDropDownMenu.setOnItemClickListener { parent, view, position, id ->
            calculateResult()
        }
    }




    private fun initilalsViews(){
        convertButton=findViewById(R.id.convertbutton)
        amountFiled=findViewById(R.id.amountValue)
        resultFiled=findViewById(R.id.resultvalue)
        toDropDownMenu=findViewById(R.id.convert_to)
        fromDropDownMenu=findViewById(R.id.from_out_comptlete_textview)
        toolbar=findViewById(R.id.toolbar)
    }



    private fun populateDropDownManu(){
        val listOfCountry= listOf(egyptianPound,americanDoller,AED,GBP)
        val adapter=ArrayAdapter(this,R.layout.drop_down_list_item,listOfCountry)
        toDropDownMenu.setAdapter(adapter)
        fromDropDownMenu.setAdapter(adapter)
    }



    private fun calculateResult(){
        if(amountFiled.text.toString().isNotEmpty()) {
            var amount = amountFiled.text.toString().toDouble()
            val currency_to_filed_menu = values[toDropDownMenu.text.toString()]
            val currency_from_filed_menu = values[fromDropDownMenu.text.toString()]

            var valueResult = amount.times(currency_to_filed_menu!!.div(currency_from_filed_menu!!))
            val formattedResult=String.format("%.3f",valueResult)
            resultFiled.setText(formattedResult)
        }
        else{
            amountFiled.setError("amount filed requred")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

}



