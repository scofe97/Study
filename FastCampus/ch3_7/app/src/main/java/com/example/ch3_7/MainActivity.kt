package com.example.ch3_7

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ch3_7.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.naver.maps.map.widget.LocationButtonView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMapReadyCallback, Overlay.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private val mapView : MapView by lazy{
        binding.mapView
    }

    private val viewPager : ViewPager2 by lazy {
        binding.houseViewPager
    }

    private val viewPagerAdapter = HouseViewPagerAdapter(itemClicked = {
        // 외부 공유를 위한 함수
        val intent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "[지금 이 가격에 예약하세요!!] ${it.title} ${it.price} 사진보기 : ${it.imgUrl}")
                type = "text/plain"
            }
        startActivity(Intent.createChooser(intent, null))
    })
    private val recyclerAdapter = HouseListAdapter()

    private  val recyclerView : RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }
    private val currentLocationButton : LocationButtonView by lazy {
        binding.currentLocationButton
    }
    private val bottomSheetTitleTextView : TextView by lazy {
        findViewById(R.id.bottomSheetTitleTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)

        // OnMapReadyCallback 을 사용하기 위해 사용함 onMapReady 를 사용함
        mapView.getMapAsync(this)

        viewPager.adapter = viewPagerAdapter

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // 뷰페이저에 나온 위치로 이동함함
               val selectedHouseModel = viewPagerAdapter.currentList[position]
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(selectedHouseModel.lat, selectedHouseModel.lng))
                    .animate(CameraAnimation.Easing)

                naverMap.moveCamera(cameraUpdate)
            }
        })
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        // 네이버 맵의 설정 기능 ( 줌 (범위 설정 가능)
        // 제한을 두지 않으면 세계지도까지 축소가 되고,  매우 좁게 확대도 됨
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        // 기본 위치 변경
        // 위경도 주소로 업데이트 해줌
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(35.234156, 129.013315))
        naverMap.moveCamera(cameraUpdate)

        // 현재 위치 얻어오기 -> 권한 필요
        val  uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false

        currentLocationButton.map = naverMap

        locationSource = FusedLocationSource(this@MainActivity, LOCATION_PERMISSION_REQUEST_CODE )

        naverMap.locationSource = locationSource

        getHouseListFromAPI()
    }

    private fun getHouseListFromAPI(){
        // Retrofit 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HouseService::class.java).also{ it ->
            it.getHouseList()
                .enqueue(object : Callback<HouseDto>{
                    override fun onResponse(call: Call<HouseDto>, response: Response<HouseDto>) {
                        if(!response.isSuccessful.not()){
                            // 실패 처리에 대한 구현
                        }

                        response.body()?.let{ dto ->
                            updateMarker(dto.items)
                            viewPagerAdapter.submitList(dto.items)
                            recyclerAdapter.submitList(dto.items)

                            bottomSheetTitleTextView.text = "${dto.items.size}개의 숙소"
                        }
                    }

                    override fun onFailure(call: Call<HouseDto>, t: Throwable) {
                        // 실패 처리에 대한 문구
                    }

                })
        }
    }

    private fun updateMarker(house: List<HouseModel>){
        house.forEach{ house ->
            Log.d("Retrofit", house.toString())
            val marker = Marker()
            marker.position = LatLng(house.lat, house.lng)
            marker.onClickListener = this

            marker.map = naverMap
            marker.tag = house.id
            marker.icon = MarkerIcons.BLACK
            marker.iconTintColor = Color.RED
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode != LOCATION_PERMISSION_REQUEST_CODE)
            return

        // 현재 라이브러리 사용이용은 권한 팝업을 쉽게 만들기 위해서임
        if(locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults)){
            if(!locationSource.isActivated){
                // 권한이 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        // 메모리가 별로 없을 때 불러지는 함수
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onClick(overlay: Overlay): Boolean {
        overlay.tag

        // 제일 먼저 나오는 아이템 반환 없으면 null
        // id가 같은거를 꺼냄
        val selectedModel = viewPagerAdapter.currentList.firstOrNull{
            it.id == overlay.tag
        }

        selectedModel?.let{
            val position = viewPagerAdapter.currentList.indexOf(it)
            viewPager.currentItem = position
        }

        return true
    }
}