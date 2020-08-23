package com.ar.maloba.runnertracking.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ar.maloba.runnertracking.Constants
import com.ar.maloba.runnertracking.data.RunningDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRunningDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, RunningDatabase::class.java, Constants.RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharePreference(@ApplicationContext app: Context) = app.getSharedPreferences(
        Constants.SHARE_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun provideName(sharePref: SharedPreferences) = sharePref.getString(Constants.KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeigth(sharePref: SharedPreferences) = sharePref.getFloat(Constants.KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharePref: SharedPreferences) = sharePref.getBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true)
}