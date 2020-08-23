package com.ar.maloba.runnertracking.di

import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import com.ar.maloba.runnertracking.Constants
import com.ar.maloba.runnertracking.Constants.KEY_FIRST_TIME_TOGGLE
import com.ar.maloba.runnertracking.Constants.KEY_NAME
import com.ar.maloba.runnertracking.Constants.KEY_WEIGHT
import com.ar.maloba.runnertracking.Constants.NOTIFICATION_CHANNEL_ID
import com.ar.maloba.runnertracking.Constants.SHARE_PREFERENCES_NAME
import com.ar.maloba.runnertracking.ui.MainActivity
import com.ar.maloba.runnertracking.R
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext app: Context
    ) = FusedLocationProviderClient(app)

    @ServiceScoped
    @Provides
    fun provideMainActivityPendingIntent(
        @ApplicationContext app: Context
    ) = PendingIntent.getActivity(
        app,
        0,
        Intent(app, MainActivity::class.java).also {
            it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENTE
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext app: Context,
        pendingIntent: PendingIntent
    ) = NotificationCompat.Builder(app, NOTIFICATION_CHANNEL_ID)
    .setAutoCancel(false)
    .setOngoing(true)
    .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
    .setContentTitle(app.getString(R.string.app_name))
    .setContentText("00:00:00")
    .setContentIntent(pendingIntent)

}