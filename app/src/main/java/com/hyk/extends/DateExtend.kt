package com.hyk.extends

import android.text.TextUtils
import org.apache.commons.lang3.time.FastDateFormat
import java.util.*

private val TIME_ZONE: TimeZone = TimeZone.getDefault() //TimeZone.getTimeZone("Asia/Seoul")

//const val formatYMD: String = "yyyy-MM-dd"
const val formatAll: String = "yyyy-MM-dd HH:mm"

fun Long.convertDate() : String {
    return if(this > 0) {
        FastDateFormat.getInstance(formatAll, TIME_ZONE).format(this)
    } else String.empty
}

fun CharSequence.convertToLong() : Long {
    return if(!TextUtils.isEmpty(this)) {
        FastDateFormat.getInstance(formatAll, TIME_ZONE).parse(this.toString()).time
    } else 0
}