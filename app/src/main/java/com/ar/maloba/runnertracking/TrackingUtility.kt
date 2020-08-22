package com.ar.maloba.runnertracking

import android.Manifest
import android.content.Context
import android.os.Build
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*

object TrackingUtility {

    private val formatterFullTimer by lazy { SimpleDateFormat(Constants.TIME_FULL_FORMAT) }
    private val formatterShortTimer by lazy { SimpleDateFormat(Constants.TIME_SHORT_FORMAT) }
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
}