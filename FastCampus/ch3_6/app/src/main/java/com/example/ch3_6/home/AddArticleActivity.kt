package com.example.ch3_6.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.ch3_6.databinding.ActivityAddArticleBinding
import com.example.ch3_6.DBkey.Companion.DB_ARTICLES
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class AddArticleActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddArticleBinding

    private var selectedUri : Uri? = null

    private  val auth : FirebaseAuth by lazy {
        Firebase.auth
    }

    private val storage : FirebaseStorage by lazy {
        Firebase.storage
    }

    private val articleDB : DatabaseReference by lazy {
        Firebase.database.reference.child(DB_ARTICLES)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageAddButton.setOnClickListener{
            when{
                // 이미 허가된 경우
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    startContentProvider()
                }
                // 권한 관련해서 안내창이 필요한 경우
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) ->{
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1010)
                }
            }
        }

        binding.submitButton.setOnClickListener{
            val title = binding.titleEditTExt.text.toString()
            val price = binding.priceEditText.text.toString()
            val sellerId = auth.currentUser?.uid.orEmpty()

            showProgress()

            // 중간에 이미지가 있으면 업로드 과정을 추가
            if (selectedUri != null){
                val photoUri = selectedUri ?: return@setOnClickListener
                uploadPhoto(photoUri,
                    successHandler = { uri ->
                        uploadArticle(sellerId, title, price, uri)
                    },
                    errorHandler = {
                        Toast.makeText(this,"사진 업로드 실패",Toast.LENGTH_SHORT).show()
                        hideProgress()
                    }
                    )
            }else{
                uploadArticle(sellerId, title, price, "")
            }

        }

    }
    private fun uploadPhoto(uri : Uri, successHandler : (String) -> Unit, errorHandler : () -> Unit){
        val fileName = "${System.currentTimeMillis()}.png"
        storage.reference.child("article/photo").child(fileName)
            .putFile(uri)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    // 업로드가 완료됨
                    storage.reference.child("article/photo").child(fileName).downloadUrl
                            // url 를 가져왔냐
                        .addOnSuccessListener { uri ->
                            successHandler(uri.toString())
                            // 실패함
                        }.addOnFailureListener{
                            errorHandler()
                        }
                }else{
                    // 업로드가 완료되지 못함
                    errorHandler()
                }
            }
    }

    private fun uploadArticle(sellerId : String, title : String, price : String, imageUri : String){
        val model = ArticleModel(sellerId, title, System.currentTimeMillis(), "$price 원", imageUri)
        articleDB.push().setValue(model)

        hideProgress()
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            1010 ->
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startContentProvider()
                }else{
                    Toast.makeText(this, "권한을 거부하셨습니다", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // 권한을 허가함
    private fun startContentProvider(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2020)
    }

    private fun showProgress(){
        binding.progrssBar.isVisible = true
    }
    private fun hideProgress(){
        binding.progrssBar.isVisible = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK){
            return
        }

        when(requestCode){
            2020 ->{
                // 사진의 URI 가 넘어옴
                val uri = data?.data
                if (uri != null){
                    binding.photoImageView.setImageURI(uri)
                    selectedUri = uri
                }else{
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else ->{
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPermissionContextPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("사진을 가져오기 위해 필요합니다.")
            .setPositiveButton("동의") { _, _ -> requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1010) }
            .create()
            .show()
    }
}