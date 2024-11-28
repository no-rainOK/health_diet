package com.example.health

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.LocationClient
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer

class MapApplication: Application() {
    override fun onCreate() {
        super.onCreate()
// 同意隐私政策
        SDKInitializer.setAgreePrivacy(this, true)
        LocationClient.setAgreePrivacy(true);
        Log.d("MapApplication", "已经同意隐私政策.")

        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        Log.d("MapApplication", "初始化 SDK，context: $this")
        try {
            SDKInitializer.initialize(this)

            Log.d("MapApplication", "SDK 初始化成功")
        } catch (e: Exception) {
            Log.e("MapApplication", "SDK 初始化失败: ${e.message}")
            e.printStackTrace()
        }




        // 自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型。
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }
}