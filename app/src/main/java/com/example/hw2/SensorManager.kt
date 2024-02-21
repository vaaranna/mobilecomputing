package com.example.hw2

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.app.NotificationCompat
import kotlin.math.abs

class SensorManager (context: Context, private val onMotionDetected: () -> Unit){

    var GYRO_THRESHOLD = 0.5;
    private val sensorEventListener = object : SensorEventListener {
        @SuppressLint("SuspiciousIndentation")
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                val xRotation = event.values[0]
                val yRotation = event.values[1]
                val zRotation = event.values[2]

                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                if (abs(xRotation) > GYRO_THRESHOLD ||
                    abs(yRotation) > GYRO_THRESHOLD ||
                    abs(zRotation) > GYRO_THRESHOLD)
                    {
                    val notification = NotificationCompat.Builder(context, "channel")
                        .setSmallIcon(R.drawable.rainbow)
                        .setContentTitle("Gyroscope Movement Detected")
                        .setContentText("Your phone has been moved!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build()

                        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(1, notification)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // do nothing
        }
    }

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    fun start() {
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
    }
}



