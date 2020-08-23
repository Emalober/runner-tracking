package com.ar.maloba.runnertracking

import android.Manifest
import android.content.Context
import android.location.Location
import android.os.Build
import com.ar.maloba.runnertracking.services.Polyline
import pub.devrel.easypermissions.EasyPermissions
import java.lang.Math.round
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object TrackingUtility {

    private val formatterFullTimer by lazy { SimpleDateFormat(Constants.TIME_FULL_FORMAT) }
    private val formatterShortTimer by lazy { SimpleDateFormat(Constants.TIME_SHORT_FORMAT) }
    private val formatterDate by lazy { SimpleDateFormat(Constants.DATE_FORMAT) }
    private val offset by lazy { TimeZone.getDefault().getOffset(Date().time) }

    fun hasLocationPermissions(context: Context)  =
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.hasPermissions(context,
            Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            EasyPermissions.hasPermissions(context,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

    fun getFormattedStopWatchTime(ms: Long, includeMillis: Boolean = false): String {
        return if(includeMillis)
            formatterFullTimer.format(Date(ms - offset))
        else
            formatterShortTimer.format(Date(ms - offset))
    }

    fun getFormattedDate(timestamp: Long): String {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
        return formatterDate.format(calendar.time)
    }

    fun calculatePolylineLength(polyline: Polyline): Float {
        var distance = 0f
        for(i in 0..polyline.size - 2) {
            val pos1 = polyline[i]
            val pos2 = polyline[i + 1]

            val result = FloatArray(1)
            Location.distanceBetween(
                pos1.latitude, pos1.longitude,
                pos2.latitude, pos2.longitude,
                result)
            distance += result.first()
        }
        return distance
    }

    fun calculateAvgSpeed(distance: Int, timeInMillis: Long) = ((distance / 1000f) / (timeInMillis / 1000f / 60 / 60) * 10).roundToInt() / 10f
}