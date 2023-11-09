package com.example.catchmoment

import android.R
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.catchmoment.mainscreen.MainActivity


const val TITLE_MSG = "Поймать момент!!!"
const val MSG = "Нажмите чтобы сделать момент!!!"

class MyService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, "1")
            .setContentTitle(TITLE_MSG)
            .setContentText(MSG)
            .setSmallIcon(R.drawable.ic_menu_camera)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        return START_NOT_STICKY
    }
}