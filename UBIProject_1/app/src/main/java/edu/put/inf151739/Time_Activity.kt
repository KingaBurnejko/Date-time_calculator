package edu.put.inf151739

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Time_Activity : AppCompatActivity() {

    lateinit var h1Input: EditText
    lateinit var m1Input: EditText
    lateinit var s1Input: EditText
    lateinit var h2Input: EditText
    lateinit var m2Input: EditText
    lateinit var s2Input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        // initialize input fields
        h1Input = findViewById(R.id.H_1_input)
        m1Input = findViewById(R.id.M_1_input)
        s1Input = findViewById(R.id.S_1_input)
        h2Input = findViewById(R.id.H_2_input)
        m2Input = findViewById(R.id.M_2_input)
        s2Input = findViewById(R.id.S_2_input)

        // initialize buttons
        val plusButton = findViewById<View>(R.id.button_plus)
        val minusButton = findViewById<View>(R.id.button_minus)
        val acButton = findViewById<View>(R.id.button_AC)

        plusButton.setOnClickListener {
            performCalculation(Operation.PLUS)
        }

        minusButton.setOnClickListener {
            performCalculation(Operation.MINUS)
        }

        acButton.setOnClickListener {
            resetFields()
        }
    }

    enum class Operation {
        PLUS, MINUS
    }

    private fun performCalculation(operation: Operation) {
        // get current input values
        val h1 = h1Input.text.toString().toIntOrNull() ?: 0
        val m1 = m1Input.text.toString().toIntOrNull() ?: 0
        val s1 = s1Input.text.toString().toIntOrNull() ?: 0
        val h2 = h2Input.text.toString().toIntOrNull() ?: 0
        val m2 = m2Input.text.toString().toIntOrNull() ?: 0
        val s2 = s2Input.text.toString().toIntOrNull() ?: 0

        // calculate result
        var resultH = h1 + h2
        var resultM = m1 + m2
        var resultS = s1 + s2

        if (operation == Operation.MINUS) {
            resultH = h1 - h2
            resultM = m1 - m2
            resultS = s1 - s2
        }

        if (resultS >= 60) {
            resultM += resultS / 60
            resultS %= 60
        } else if (resultS < 0) {
            resultM -= 1
            resultS += 60
        }

        if (resultM >= 60) {
            resultH += resultM / 60
            resultM %= 60
        } else if (resultM < 0) {
            resultH -= 1
            resultM += 60
        }

        if (resultH < 0) {
            val toast = Toast.makeText(this, "Wynik jest ujemny", Toast.LENGTH_SHORT)
            toast.duration = Toast.LENGTH_LONG
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            return
        }

        h1Input.setText(resultH.toString())
        m1Input.setText(resultM.toString())
        s1Input.setText(resultS.toString())
        h2Input.setText("0")
        m2Input.setText("0")
        s2Input.setText("0")
    }

    private fun resetFields() {
        h1Input.setText("0")
        m1Input.setText("0")
        s1Input.setText("0")
        h2Input.setText("0")
        m2Input.setText("0")
        s2Input.setText("0")
    }
}
