package com.example.health.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.example.health.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private var mMapView: MapView? = null
    private var mBaiduMap: BaiduMap? = null
    private var mLocationClient: LocationClient? = null
    private var mTextView: TextView? = null
    lateinit var binding: ActivityMapBinding

    // 是否是第一次定位
    private var isFirstLocate = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取地图控件引用
        mMapView = binding.mapView

        // 得到地图
        mBaiduMap = mMapView!!.map
        // 开启定位图层
        mBaiduMap?.isMyLocationEnabled = true

        // 定位初始化
        mLocationClient = LocationClient(this)

        // 通过LocationClientOption设置LocationClient相关参数
        val option = LocationClientOption()
        option.isOpenGps = true // 打开GPS
        option.setCoorType("bd09ll") // 设置坐标类型
        option.setScanSpan(1000) // 定位间隔
        // 可选，设置是否需要地址信息
        option.setIsNeedAddress(true)
        // 可选，设置是否需要描述信息
        option.setIsNeedLocationDescribe(true)

        // 设置locationClientOption
        mLocationClient?.locOption = option

        // 设置定位监听器
        mLocationClient?.registerLocationListener(object : BDAbstractLocationListener() {
            override fun onReceiveLocation(bdLocation: BDLocation?) {
                if (bdLocation == null) return

                // 获取当前定位信息
                val latitude = bdLocation.latitude // 纬度
                val longitude = bdLocation.longitude // 经度
                val address = bdLocation.address // 地址信息
                val locationDescribe = bdLocation.locationDescribe // 描述信息

                // 显示地址信息
                mTextView?.text = "当前地址: $address"

                // 如果是第一次定位，移动地图至用户当前位置
                if (isFirstLocate) {
                    val currentLatLng = LatLng(latitude, longitude)
                    val update = MapStatusUpdateFactory.newLatLngZoom(currentLatLng, 15f)
                    mBaiduMap?.animateMapStatus(update)
                    isFirstLocate = false
                }

                // 更新当前位置数据
                val locData = MyLocationData.Builder()
                    .latitude(latitude)
                    .longitude(longitude)
                    .build()
                mBaiduMap?.setMyLocationData(locData)
            }
        })

        // 启动定位
        mLocationClient?.start()
    }

    // 继承抽象类BDAbstractListener并重写其onReceiveLocation方法来获取定位数据
    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation?) {
            // mapView销毁后不再处理新接收的位置
            if (location == null || mMapView == null) {
                return
            }

            // 如果是第一次定位
            val ll = LatLng(location.latitude, location.longitude)
            if (isFirstLocate) {
                isFirstLocate = false
                // 给地图设置状态，缩放级别为18
                val builder = com.baidu.mapapi.map.MapStatus.Builder()
                builder.target(ll).zoom(18.0f) // 设置缩放级别为18
                mBaiduMap?.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
            }

            // 更新定位信息
            val locData = MyLocationData.Builder()
                .latitude(location.latitude)
                .longitude(location.longitude)
                .accuracy(location.radius) // 定位精度
                .build()
            mBaiduMap?.setMyLocationData(locData) // 更新地图上的定位数据

            // 显示地址信息
            if (location.hasAddr()) {
                val locationDescription = location.addrStr // 获取地址描述
                mTextView?.text = locationDescription // 在TextView中显示地址信息
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 在Activity的生命周期中同步地图控件
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        // 在Activity的生命周期中同步地图控件
        mMapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 停止定位
        mLocationClient?.stop()
        // 释放地图控件
        mMapView?.onDestroy()
    }
}