package com.mergenc.starmaker.helper.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.mergenc.starmaker.application.Constants
import com.mergenc.starmakerframework.StarMakerFramework

/**
 * Created by Mehmet Emin Ergenc on 12/30/2023
 */

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Call to reset the array
        StarMakerFramework.triggerReset(context)
        NotificationManagerCompat.from(context).cancel(Constants.NOTIFICATION_CHANNEL_ID)
    }
}