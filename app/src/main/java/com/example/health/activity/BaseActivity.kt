package com.example.health.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.math.max
import kotlin.math.min

abstract class BaseActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sm: SensorManager
    lateinit var senor: Sensor
    private var lastBrightness: Float = -1f  // ��һ�ε�����ֵ
    private var lastTime: Long = 0  // ��һ�θ���ʱ��

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ��ʼ��������
        sm = getSystemService(SENSOR_SERVICE) as SensorManager
        senor = sm.getDefaultSensor(Sensor.TYPE_LIGHT)!!

        // ��鲢����¼��Ȩ��
//        checkAndRequestPermissions()
    }

    override fun onStart() {
        super.onStart()
        // ע�ᴫ��������
        sm.registerListener(this, senor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onStop() {
        super.onStop()
        // ע������������
        sm.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        // ��ȡ�⴫������ֵ
        val brightness = event.values[0].toInt()
        Log.d("����", brightness.toString())

        // �ж����ȱ仯�Ƿ���Ҫ����
        if (System.currentTimeMillis() - lastTime > 5) {  // ÿ100ms����һ��
            val newBrightness = calculateBrightness(brightness)
            if (newBrightness != lastBrightness) {
                changeAppBrightness(this, newBrightness)
                lastBrightness = newBrightness
                lastTime = System.currentTimeMillis()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    // �����ʺϵ�����ֵ
    private fun calculateBrightness(sensorValue: Int): Float {
        // �Թ��ֵ���д���ȷ��������0-1�ķ�Χ
        val brightness = sensorValue.toFloat() / 255f
        return max(0.0f, min(0.9f, brightness))  // ȷ�����Ȳ���̫�ͻ�̫��
    }

    // �޸�Ӧ������
    private fun changeAppBrightness(context: Context, brightness: Float) {
        val window = (context as Activity).window
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp
    }

//    // ��鲢����¼��Ȩ��
//    private fun checkAndRequestPermissions() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.RECORD_AUDIO
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // ���δ����Ȩ�ޣ�����Ȩ��
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.RECORD_AUDIO),
//                1001
//            )
//        } else {
//            Log.i("BaseActivity", "¼��Ȩ��������")
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1001) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.i("BaseActivity", "¼��Ȩ��������")
//            } else {
//                Toast.makeText(this, "¼��Ȩ��δ���裬���ֹ��ܿ����޷�ʹ��", Toast.LENGTH_SHORT)
//                    .show()
//                // �ṩ��ת�����õ�ѡ��
//                showPermissionSettings()
//            }
//        }
//    }
//
//    // �ṩ��ת��Ȩ������ҳ���ѡ��
//    private fun showPermissionSettings() {
//        Toast.makeText(this, "��ǰ�������ֶ�����¼��Ȩ��", Toast.LENGTH_LONG).show()
//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        intent.data = Uri.fromParts("package", packageName, null)
//        startActivity(intent)
//    }
}
