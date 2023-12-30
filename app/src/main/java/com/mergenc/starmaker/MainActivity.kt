package com.mergenc.starmaker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mergenc.starmaker.application.Constants.IMAGE_URL
import com.mergenc.starmaker.application.Constants.NOTIFICATION_CHANNEL_ID
import com.mergenc.starmaker.databinding.ActivityMainBinding
import com.mergenc.starmaker.helper.notification.NotificationReceiver
import com.mergenc.starmakerframework.StarMakerFramework
import com.mergenc.starmakerframework.data.misc.Size

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val starMakerFramework = StarMakerFramework(this)

    private var isInBackground = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createNotificationChannel()

        binding.wvStarMaker.loadUrl(IMAGE_URL)

        binding.mcvSmallStar.setOnClickListener {
            // Call the method to add a small star from your framework
            starMakerFramework.addStarInterface(Size.S)
        }

        binding.mcvBigStar.setOnClickListener {
            // Call the method to add a big star from your framework
            starMakerFramework.addStarInterface(Size.B)
        }

        binding.mcvReset.setOnClickListener {
            // Call the method to reset the stars from your framework; this will also reset the stars in the notification
            StarMakerFramework.triggerReset(this)
        }
    }

    // This method is called when the app is sent to background; it will show a notification after 5 seconds
    override fun onPause() {
        super.onPause()
        isInBackground = true
        handler.postDelayed({
            if (isInBackground) {
                showNotification()
            }
        }, 5000) // Delay of 5 seconds
    }

    // This method is called when the app is brought to foreground; it will cancel the notification
    override fun onResume() {
        super.onResume()
        isInBackground = false
        cancelNotification()
    }

    private fun showNotification() {
        val notificationTitleText: String = resources.getString(R.string.notification_content_title)
        val notificationContentText: String =
            resources.getString(R.string.notification_content_text)
        val notificationActionText: String = resources.getString(R.string.notification_action_text)

        val resetIntent = Intent(this, NotificationReceiver::class.java)
        val resetPendingIntent =
            PendingIntent.getBroadcast(this, 0, resetIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, "3308")
            .setSmallIcon(R.drawable.ic_star)
            .setContentTitle(notificationTitleText)
            .setContentText(notificationContentText)
            .addAction(
                R.drawable.ic_star,
                notificationActionText,
                resetPendingIntent
            )

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(this).notify(NOTIFICATION_CHANNEL_ID, builder.build())
    }

    // This method is called when the notification is cancelled
    private fun cancelNotification() {
        NotificationManagerCompat.from(this).cancel(NOTIFICATION_CHANNEL_ID)
    }

    // This method is called when the app is first opened; it will create a notification channel
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "StarMakerInsider"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("3308", name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}