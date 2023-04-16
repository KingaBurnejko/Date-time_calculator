package edu.put.inf151739

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Date_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        val datePicker1: DatePicker = findViewById(R.id.date_1)
        val datePicker2: DatePicker = findViewById(R.id.date_2)
        val resultText1: EditText = findViewById(R.id.result_1)
        val resultText2: TextView = findViewById(R.id.result_2)
        val buttonPlusDate: Button = findViewById(R.id.button_plus_date)

        fun updateResults() {
            val calendar1 = Calendar.getInstance().apply {
                set(datePicker1.year, datePicker1.month, datePicker1.dayOfMonth)
            }
            val calendar2 = Calendar.getInstance().apply {
                set(datePicker2.year, datePicker2.month, datePicker2.dayOfMonth)
            }
            val daysBetween = daysBetween(calendar1.time, calendar2.time)
            val workingDaysBetween = workingDaysBetween(calendar1.time, calendar2.time)

            resultText1.setText(daysBetween.toString())
            resultText2.text = workingDaysBetween.toString()
        }

        datePicker1.init(
            datePicker1.year, datePicker1.month, datePicker1.dayOfMonth
        ) { _, _, _, _ -> updateResults() }

        datePicker2.init(
            datePicker2.year, datePicker2.month, datePicker2.dayOfMonth
        ) { _, _, _, _ -> updateResults() }

        buttonPlusDate.setOnClickListener {
            val daysToAdd = resultText1.text.toString().toIntOrNull() ?: 0
            val calendar = Calendar.getInstance().apply {
                set(datePicker1.year, datePicker1.month, datePicker1.dayOfMonth)
                add(Calendar.DAY_OF_MONTH, daysToAdd)
            }
            datePicker2.updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            updateResults()
        }

        updateResults()
    }

    private fun daysBetween(date1: Date, date2: Date): Int {
        val diff = date2.time - date1.time
        return (diff / (1000 * 60 * 60 * 24)).toInt()
    }

    private fun workingDaysBetween(date1: Date, date2: Date): Int {
        var workingDays = 0

        val calendar = Calendar.getInstance()
        calendar.time = date1

        while (calendar.time.time <= date2.time) {
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val year = calendar.get(Calendar.YEAR)
            val easterSunday = calculateEasterSunday(year)

            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY && !isHoliday(
                    calendar.time,
                    year,
                    easterSunday
                )
            ) {
                workingDays++
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return workingDays
    }

    private fun isHoliday(date: Date, year: Int, easterSunday: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Fixed holidays
        val fixedHolidays = arrayOf(
            "1-1", "1-6", "5-1", "5-3", "8-15", "11-1", "11-11", "12-25", "12-26"
        )
        val dateString = "$month-$day"
        if (fixedHolidays.contains(dateString)) {
            return true
        }

        // Easter and other movable holidays
        val easterCalendar = Calendar.getInstance()
        easterCalendar.time = easterSunday

        val holidaysRelativeToEaster = arrayOf(
            0,  // Easter Sunday
            1,  // Easter Monday
            60  // Corpus Christi
        )

        for (daysOffset in holidaysRelativeToEaster) {
            easterCalendar.time = easterSunday
            easterCalendar.add(Calendar.DAY_OF_MONTH, daysOffset)

            if (calendar.get(Calendar.DAY_OF_MONTH) == easterCalendar.get(Calendar.DAY_OF_MONTH) &&
                calendar.get(Calendar.MONTH) == easterCalendar.get(Calendar.MONTH)
            ) {
                return true
            }
        }

        // Special cases
        if (year == 2005 && month == 4 && day == 8) {
            return true
        }

        if (year == 2018 && month == 11 && day == 12) {
            return true
        }

        return false
    }

    private fun calculateEasterSunday(year: Int): Date {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val p = (h + l - 7 * m + 114) % 31

        val day = p + 1
        val month = (h + l - 7 * m + 114) / 31

        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)

        return calendar.time
    }
}
