package com.amir.datepickerdialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tvDate: TextView
    lateinit var tvDateDiffernece: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDate = findViewById<Button>(R.id.btn_date)
        tvDate = findViewById<TextView>(R.id.tv_date)
        tvDateDiffernece = findViewById<TextView>(R.id.tv_dateInMinute)


        btnDate.setOnClickListener {
            clickDatePicker()
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //select date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                tvDate.text = selectedDate

                //formating
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    //convert to minute
                    val selectedDataInMinute = theDate.time / 60000
//getting current time and convert
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinute = currentDate.time / 60000
                    val differenceInMinute = currentDateInMinute - selectedDataInMinute
                    tvDateDiffernece.text = differenceInMinute.toString()
                }

            },
            year,
            month,
            day
        )
        //to set the datePicker until today
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}