package edu.put.inf151739

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent;
import android.view.View;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun runClickDate(v: View){
        goToActivity(Date_Activity::class.java)
    }

    fun runClickTime(v: View){
        goToActivity(Time_Activity::class.java)
    }

    private fun goToActivity(activityClass: Class<*>) {
        val i = Intent(this, activityClass)
        startActivity(i)
    }
}