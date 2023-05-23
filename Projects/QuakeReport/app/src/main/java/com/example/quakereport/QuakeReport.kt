package com.example.quakereport

import java.text.DecimalFormat

class QuakeReport(
    mag: Double,
    time: Long,
    title: String,
    place: String,
) {
    val mag = DecimalFormat("0.0").format(mag)
    val time = time
    val title = title
    val place = place


}