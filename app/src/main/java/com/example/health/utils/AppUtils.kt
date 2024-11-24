package com.example.health.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AppUtils private constructor() {

    private val pref: SharedPreferences = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private const val PREF_NAME = "app_pref"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        private val instance: AppUtils by lazy { AppUtils() }

        fun isFirstTimeLaunch(): Boolean = instance.pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)

        fun setFirstTimeLaunch(isFirstTime: Boolean) {
            instance.editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply()
        }

        val application: Application by lazy {
            try {
                val activityThreadClass = Class.forName("android.app.ActivityThread")
                val activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null)
                activityThreadClass.getMethod("getApplication").invoke(activityThread) as Application
            } catch (e: Exception) {
                throw IllegalStateException("Unable to get Application instance", e)
            }
        }
    }
}
