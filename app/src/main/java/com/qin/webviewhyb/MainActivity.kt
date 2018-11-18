package com.qin.webviewhyb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mWebView: WebView by lazy {
        webview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setWebView()

    }

    var setWebView = {
        mWebView.settings.javaScriptEnabled = true
        mWebView.webViewClient = MyWebViewClient()
        mWebView.webChromeClient = MyWebChromeClient()
        mWebView.addJavascriptInterface(JavaScriptMethod, "javaScriptMethod")
        mWebView.loadUrl("http://192.168.191.1:8080/js/jas.html")
    }

    object JavaScriptMethod {

        //javascript调用android中的方法
        fun showToast(msg: String) {
            Log.i("", "showToast")
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //android->javascript
            mWebView.loadUrl("javascript:方法名（参数）")
        }
    }

    private class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

        }
    }
}
