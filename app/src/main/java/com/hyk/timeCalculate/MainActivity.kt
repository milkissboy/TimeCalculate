package com.hyk.timeCalculate

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.hyk.extends.convertDate
import com.hyk.extends.convertToLong
import com.hyk.extends.empty
import com.hyk.extends.space
import com.hyk.timeCalculate.databinding.ActivityMainBinding
import com.hyk.timeCalculate.dialog.DatePickerFragment
import com.hyk.timeCalculate.dialog.TimePickerFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    private var current: Long = 0
    private var set: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListener()
        timeDataReplace()
    }

    private fun initListener() {
        binding.layCurrent.setOnClickListener {
            timeDataReplace()
        }
    }

    private fun timeDataReplace() {
        current = System.currentTimeMillis()
        binding.tvCurrent.text = TextUtils.concat("current : ", current.convertDate())

        if(set > 0) {
            calculate()
        }
    }

    fun showDatePicker(v: View) {
        val fragment = DatePickerFragment {
            showTimePicker(it)
        }
        fragment.show(supportFragmentManager, "datePicker")
    }

    private fun showTimePicker(date: String) {
        val fragment = TimePickerFragment {
            val setStr = TextUtils.concat(date, String.space, it)
            set = setStr.convertToLong()
            binding.tvSet.text = TextUtils.concat("set : ", setStr)
            calculate()
        }
        fragment.show(supportFragmentManager, "timePicker")
    }

    private fun calculate() {
        if (set < current) {
            calculateLogic(current - set, "전...")
        } else {
            calculateLogic(set - current, "후...")
        }
    }

    private fun calculateLogic(different: Long, prefix: String) {

        val oneSec = 1000L
        val oneMin: Long = 60 * oneSec
        val oneHour: Long = 60 * oneMin
        val oneDay: Long = 24 * oneHour
        val oneMonth: Long = 30 * oneDay
        val oneYear: Long = 365 * oneDay

        val diffMin: Long = different / oneMin
        val diffHours: Long = different / oneHour
        val diffDays: Long = different / oneDay
        val diffMonths: Long = different / oneMonth
        val diffYears: Long = different / oneYear

        var resultText = String.empty

        when {
            diffYears > 0 -> {
                resultText = TextUtils.concat(diffYears.toString(), " 년 ", prefix).toString()
            }
            diffMonths > 0 && diffYears < 1 -> {
                resultText =
                    TextUtils.concat((diffMonths - diffYears / 12).toString(), " 달 ", prefix)
                        .toString()
            }
            diffDays > 0 && diffMonths < 1 -> {
                resultText =
                    TextUtils.concat((diffDays - diffMonths / 30).toString(), " 일 ", prefix)
                        .toString()
            }
            diffHours > 0 && diffDays < 1 -> {
                resultText =
                    TextUtils.concat((diffHours - diffDays * 24).toString(), " 시간 ", prefix)
                        .toString()
            }
            diffMin > 0 && diffHours < 1 -> {
                resultText = TextUtils.concat((diffMin - diffHours * 60).toString(), " 분 ", prefix)
                    .toString()
            }
            diffMin < 1 -> {
                resultText = "지금 .."
            }
        }

        binding.tvResult.text = resultText
        Log.d("test2", resultText)
    }
}