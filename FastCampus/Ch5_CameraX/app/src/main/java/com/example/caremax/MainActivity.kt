package com.example.caremax

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.display.DisplayManager
import android.media.Image
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.camera2.internal.annotation.CameraExecutor
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.FLASH_MODE_AUTO
import androidx.camera.core.impl.ImageOutputConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.caremax.databinding.ActivityMainBinding
import com.example.caremax.extensions.loadCenterCrop
import com.example.caremax.util.PathUtil
import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 카메라 실행자?
    private lateinit var cameraExecutor: ExecutorService
    private val cameraMainExecutor by lazy { ContextCompat.getMainExecutor(this)}

    // 카메라 인스턴스
    private var camera : Camera? = null
    private val cameraProviderFuture by lazy { ProcessCameraProvider.getInstance(this) }

    private var root: View? =  null

    private var isCaputring : Boolean = false

    // 이미지 캡쳐
    private lateinit var imageCapture: ImageCapture

    // 디스플레이 매니저
    private val displayManager by lazy {
        getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }
    private var displayId: Int = -1

    // 디스플레이 리스너 -> 디스플레이 변경(회전) 같은 값을 얻기위해
    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {
            TODO("Not yet implemented")
        }

        override fun onDisplayRemoved(displayId: Int) {
            TODO("Not yet implemented")
        }

        // 화면변화 감지
        override fun onDisplayChanged(displayId: Int) {
            if (this@MainActivity.displayId == displayId) {
                // 화면회전 감지
                if(::imageCapture.isInitialized && root != null){
                    imageCapture.targetRotation = root?.display?.rotation ?: ImageOutputConfig.INVALID_ROTATION
                }
            }
        }

    }

    private var uriList = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        root = binding.root
        setContentView(binding.root)

        if (allPermissionGranted()) {
            startCamera(binding.viewFinder)
        } else {
            requestPermissions()
        }
    }

    // 권한체크
    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    // 권한처리
    private val askMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                Log.d(TAG, "name = $permissionName , grant = $isGranted")
                if (isGranted) {
                    // 권한이 허용되었는가?
                    startCamera(binding.viewFinder)
                } else {
                    // 권한이 거부되었는가?
                    Toast.makeText(this, "권한이 거부 되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // 권한요청
    private fun requestPermissions() {
        askMultiplePermissionsLauncher.launch(REQUIRED_PERMISSIONS)
    }


    // 카메라 관리
    private fun startCamera(viewFinder: PreviewView) {
        // 리스너, 핸들러값 넣어줌
        displayManager.registerDisplayListener(displayListener, null)
        // 실행자 (새로운 스레드 기반 실행)
        cameraExecutor = Executors.newSingleThreadExecutor()
        viewFinder.postDelayed({
            // 현재 카메라가 보여지고있는 id
            displayId = viewFinder.display.displayId
            bindCameraUseCase()
        }, 10)
    }

    // 카메라 실행 ( UseCase 받아옴 )
    private fun bindCameraUseCase() = with(binding) {
        // 회전값 가져옴
        val rotation = viewFinder.display.rotation
        // 정면 후면 인식을위한 선택자
        val cameraSelector = CameraSelector.Builder().requireLensFacing(LENS_FACING).build()

        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().apply {
                setTargetAspectRatio(AspectRatio.RATIO_4_3)
                setTargetRotation(rotation)
                // 해상도지정
                // setTargetResolution()
            }.build()

            val imageCaptureBuilder = ImageCapture.Builder()
                // 지연 최소화
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(rotation)
                .setFlashMode(FLASH_MODE_AUTO)

            imageCapture = imageCaptureBuilder.build()

            try{
                // 기존에 바인딩 되어있는 카메라는 해제를 해줌
                cameraProvider.unbindAll()

                // 사용할 개체를 가져옴
                camera = cameraProvider.bindToLifecycle(
                    this@MainActivity, cameraSelector, preview, imageCapture
                )
                // 화면상 매치?
                preview.setSurfaceProvider(viewFinder.surfaceProvider)
                bindCaptureListener()

            }catch (e : Exception){
                e.printStackTrace()
            }
        }, cameraMainExecutor)
    }

    private fun bindCaptureListener() = with(binding){
        captureButton.setOnClickListener{
            if (!isCaputring){
                isCaputring = true
                captureCamera()
            }
        }
    }

    // 컨텐트 Uri 를 받고 null 이 아니면 갤러리 지정
    private fun updateSaveImageContent() {
        contentUri?.let{
            isCaputring = try{
                val file = File(PathUtil.getPath(this,it) ?: throw FileNotFoundException())
                MediaScannerConnection.scanFile(this, arrayOf(file.path), arrayOf("image/jpeg"), null)
                Handler(Looper.getMainLooper()).post{
                    binding.previewImageView.loadCenterCrop(it.toString(), corner = 4f)
                }
                uriList.add(it)
                false
            }catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this,"파일이 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    private var contentUri: Uri? = null

    private fun captureCamera(){
        if(::imageCapture.isInitialized.not()) return
        // 외장 디렉토리에 저장
        val photoFile = File(
            PathUtil.getOutputDirectory(this),
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.KOREA
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions =  ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                contentUri = savedUri
                updateSaveImageContent()

            }

            override fun onError(e: ImageCaptureException) {
                e.printStackTrace()
                isCaputring = false
            }

        })
    }


    companion object {
        const val TAG = "MainActivity"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val LENS_FACING: Int = CameraSelector.LENS_FACING_BACK

        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}