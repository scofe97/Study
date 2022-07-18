package com.example.ch4_3

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ch4_3.databinding.ActivityMapBinding
import com.example.ch4_3.model.LocationLatLngEntity
import com.example.ch4_3.model.SearchResultEntity
import com.example.ch4_3.utillity.RetrofitUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MapActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope{

    private lateinit var binding : ActivityMapBinding
    private lateinit var map : GoogleMap
    private var currentSelectMarker : Marker? = null
    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener : MyLocationListener

    private lateinit var searchResult : SearchResultEntity

    private lateinit var job: Job

    companion object{
        const val SEARCH_RESULT_EXTRA_KEY ="SEARCH_RESULT_EXTRA_KEY"
        const val CAMERA_ZOOM_LEVEL = 17f
        const val PERMISSION_REQUEST_CODE = 1
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        if(::searchResult.isInitialized.not()){
            intent?.let{
                // Intent 로 정보받아옴 (Entity)
                searchResult = it.getParcelableExtra<SearchResultEntity>(SEARCH_RESULT_EXTRA_KEY) ?: throw Exception("데이터가 존재하지 않음")
                // 지도 화면에 뛰움
                setupGoogleMap()
            }
        }
        bindView()
    }

    private fun bindView() = with(binding){
        currentLocationButton.setOnClickListener{
            getMyLocation()
        }
    }

    private fun setupGoogleMap(){
        // 프래그먼트 연결
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        // 프래그먼트에 콜백 등록 ( OnMapReadyCallback )
        mapFragment.getMapAsync(this)

    }

    // OnMapReadyCallback -> 구현
    override fun onMapReady(map: GoogleMap) {
        this.map = map
        currentSelectMarker = setupMarker(searchResult)

        currentSelectMarker?.showInfoWindow()
    }

    // 마커구현 (Entity 정보를 토대로)
    private fun  setupMarker(searchResultEntity: SearchResultEntity) : Marker{
        // 위도/경도 정보를 가져와서 LatLng 클래스 생성
        val positionLatLng = LatLng(
            searchResultEntity.locationLatLng.latitude.toDouble(), searchResultEntity.locationLatLng.longitude.toDouble()
        )

        // 마커 옵션정의 (위치,  제목,  스니펫)
        val markerOption = MarkerOptions().apply{
            position(positionLatLng)
            title(searchResultEntity.name)
            snippet(searchResultEntity.fullAddress)
        }

        // 구글 지도 움직임 ( 위치설정,  줌레벨 17 )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, CAMERA_ZOOM_LEVEL))

        // 마커 추가
        return map.addMarker(markerOption)
    }


    // 내 위치로 카메라 이동
    private fun getMyLocation(){
        // locationManager 을 이용해서 현재정보 받아옴
        if (::locationManager.isInitialized.not()) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        //  GPS_PROVIDER 사용가능한가
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (isGpsEnable) {
            // 권한 허가 안된경우
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // 권한을 가져옴
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                setMyLocationListener()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime = 1500L
        val minDistance = 100f

        // 이벤트 리스너 생성
        if(::myLocationListener.isInitialized.not()){
            myLocationListener = MyLocationListener()
        }

        // 좌표값 생성
        with(locationManager){

            // GPS_PROVIDER -> GPS 를 이용해 위치제공
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )

            // NETWORK_PROVIDER -> WIFI 접근 포인트들을 이용하여 측정함
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    private fun loadReverseGeoInformation(locationEntity: LocationLatLngEntity) {
        launch(coroutineContext) {
            try{
                withContext(Dispatchers.IO){

                    // 데이터를 받아옴 (내위치)
                    val response = RetrofitUtil.apiService.getReverseGeoCode(
                        lat = locationEntity.latitude.toDouble(),
                        lon = locationEntity.longitude.toDouble()
                    )
                    if(response.isSuccessful){
                        val body = response.body()

                        withContext(Dispatchers.Main){
                            Log.e("List", body.toString())
                            body?.let{
                                currentSelectMarker = setupMarker(SearchResultEntity(
                                    fullAddress = it.addressInfo.fullAddress ?: "주소 정보 없음",
                                    name = "내 위치",
                                    locationLatLng = locationEntity
                                ))
                                currentSelectMarker?.showInfoWindow()
                            }
                        }
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this@MapActivity, "에러", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 리스너 제거
    private fun removeLocationListener(){
        if(::locationManager.isInitialized && ::myLocationListener.isInitialized){
            locationManager.removeUpdates(myLocationListener)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                setMyLocationListener()
            }else{
                Toast.makeText(this,"권한 받지못함", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // 움직인 좌표로 카메라 이동하게함
    private fun onCurrentLocationChanged(locationLatLngEntity : LocationLatLngEntity){

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
            LatLng(
                locationLatLngEntity.latitude.toDouble(),
                locationLatLngEntity.longitude.toDouble()
            ), CAMERA_ZOOM_LEVEL))

        loadReverseGeoInformation(locationLatLngEntity)
        removeLocationListener()

    }

    inner class MyLocationListener : LocationListener{

        override fun onLocationChanged(location: Location) {
            // 위치가 변함을 감지
            val locationLatLngEntity = LocationLatLngEntity(
                location.latitude.toFloat(),
                location.longitude.toFloat()
            )
            onCurrentLocationChanged(locationLatLngEntity)
        }
    }
}