package com.example.ch2_8

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ch2_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val webView : WebView by lazy {
        binding.webView
    }

    private val addressBar : EditText by lazy {
        binding.addressBar
    }

    private val goHomeButton : ImageButton by lazy{
        binding.goHomeButton
    }

    private val goBackButton : ImageButton by lazy{
        binding.goBackButton
    }

    private val goForwardButton : ImageButton by lazy {
        binding.goForwardButton
    }

    private  val refreshLayout : SwipeRefreshLayout by lazy{
        binding.refreshLayout
    }

    private val progressBar : ContentLoadingProgressBar by lazy{
        binding.progressBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        // 앱 하단의 뒤로가기 버튼에 관련된 구현
        // 앱에서 뒤로갈수 있으면 앱의 뒤로가기, 없으면 앱에서 나가짐
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    private fun initViews(){
        // HTTPS 프로토콜안쓰면 보안에러나옴
        // 이를해결하기위해 매니페스트에 usesCleartextTraffic 값을 true로 줌

        // 이 코드가 없으면 외부 브라우저를 통해 주소로 이동함
        // 이동이후 버튼을 클릭하면 동작을 하지 않는데 이는 안드로이드내에서 자바스크립트를 허용안해서 그런것
        // 이를 허용해주어야 함 (이는 보안상의 문제임)
        webView.apply{
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            loadUrl(DEFAULT_URL)
        }

        //imeOption 액션 버튼을 자세히 명세하기 위해 사용
        // 검색창, 메세지, 등을 보낼 때 사용함, 우리는 입력하는 키보드를 닫는걸 사용할거임


    }

    private fun bindViews(){
        // 인자 -> 액션이 발생한 뷰, 액션 아이디, 눌렸는지 아닌지,
        // 반환 -> 액션을 소비했다면 true, 아니면 false -> 지금 true 하면 키보드 안내려가므로 false 줌
        addressBar.setOnEditorActionListener{ v, actionId, evnet ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                val loadingUrl = v.text.toString()

                // 앞에 http나 https가 있는지 확인해줌 (둘다 없으면 false)
                if(URLUtil.isNetworkUrl(loadingUrl)){
                    webView.loadUrl(v.text.toString())
                }else {
                    webView.loadUrl("http://$loadingUrl")
                }
            }

            return@setOnEditorActionListener false
        }

        goHomeButton.setOnClickListener{
            webView.loadUrl(DEFAULT_URL)
        }

        goBackButton.setOnClickListener{
            webView.goBack()
        }

        goForwardButton.setOnClickListener{
            webView.goForward()
        }

        refreshLayout.setOnRefreshListener {
            // 이벤트 리스너
            // 새로고침 해줌
            webView.reload()
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient(){

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar.show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            // 이걸 안해주면 계속해서 돌아감
            refreshLayout.isRefreshing = false
            progressBar.hide()

            goBackButton.isEnabled = webView.canGoBack()
            goForwardButton.isEnabled = webView.canGoForward()

            // 리다이렉팅 -> 최종적인 주소를 보여줌줌
            addressBar.setText(url)
        }
    }

    // 웹 뷰에서는 진행도를 모름
    // 웹 뷰 클라이언트와의 차이
    // 브라우저 차원의 자바스크립트 얼럿 이벤트, 웹 컨텐츠 타이틀 정보를 받는 이벤트등을 오버라이딩 할 때 사용
    inner class WebChromeClient : android.webkit.WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            // 현재 페이지의 로딩된 정도 0~100
            super.onProgressChanged(view, newProgress)

            progressBar.progress = newProgress
        }
    }

    companion object{
        private const val DEFAULT_URL = "http://www.google.com"
    }
}