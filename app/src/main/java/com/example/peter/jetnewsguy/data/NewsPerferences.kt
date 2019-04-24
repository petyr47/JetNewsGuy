package com.example.peter.jetnewsguy.data

import android.content.Context
import android.preference.PreferenceManager
import com.example.peter.jetnewsguy.R

object NewsPerferences {

        fun getLastSyncTimeInMillis(context: Context): Long {

            val lastSyncKey = context.getString(R.string.pref_last_sync)
            val sp = PreferenceManager.getDefaultSharedPreferences(context)
            return sp.getLong(lastSyncKey, 0)
        }


        fun getEllapsedTimeSinceLastSync(context: Context): Long {
            val lastNotificationTimeMillis = this.getLastSyncTimeInMillis(context)
            return System.currentTimeMillis() - lastNotificationTimeMillis
        }


        fun saveNewLastSyncTime(context: Context, timeOfNotification: Long) {
            val sp = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sp.edit()
            val lastNotificationKey = context.getString(R.string.pref_last_sync)
            editor.putLong(lastNotificationKey, timeOfNotification)
            editor.apply()
        }
    }

