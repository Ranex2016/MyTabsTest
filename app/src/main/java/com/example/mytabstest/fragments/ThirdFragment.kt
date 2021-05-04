package com.example.mytabstest.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mytabstest.LocListenerInterface
import com.example.mytabstest.MyLocationListener
import com.example.mytabstest.R

class ThirdFragment : Fragment(), LocListenerInterface {
    lateinit var tvCoordinate:TextView;
    public lateinit var locationManager: LocationManager
    public lateinit var myLocationListener: MyLocationListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        tvCoordinate = view.findViewById(R.id.tvCoordinate)
        init()

        return view
    }


    fun init(){

        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        myLocationListener = MyLocationListener()
        myLocationListener.setLocListenerInterface(this)
        checkPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100 && grantResults[0] != Activity.RESULT_OK){


        }else{
            Toast.makeText(context, "Нет разрешений GPS", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    public fun checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && ActivityCompat.checkSelfPermission(context!!,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context!!,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                                       Manifest.permission.ACCESS_COARSE_LOCATION), 100)

        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,1f,myLocationListener)
        }
    }

    override fun onLocChanged(location: Location?) {
        tvCoordinate.text = location?.latitude.toString() +" : "+ location?.longitude
    }

    override fun onDestroy() {
        locationManager.removeUpdates(myLocationListener)
        super.onDestroy()
    }


}