package com.example.dailyexpenseswsr.utils

import java.text.SimpleDateFormat

class HelperMethods {
    companion object{
        fun convertMillisToDate(millis: Long): String{
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            return sdf.format(millis)
        }
    }
}