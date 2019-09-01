package com.mucahitkambur.tdksozluk.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.databinding.ActivityWebViewBinding
import com.mucahitkambur.tdksozluk.model.main.Url
import com.mucahitkambur.tdksozluk.util.EXTRA_URL
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityWebViewBinding

    companion object {
        fun newIntent(context: Context, url: Url): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)

        val url = intent.getSerializableExtra(EXTRA_URL) as Url
        dataBinding.title = url.title
        dataBinding.webView.settings.javaScriptEnabled = true
        dataBinding.webView.webViewClient = WebViewClient()
        dataBinding.webView.loadUrl(url.url)

        dataBinding.toolbarCommon.setNavigationOnClickListener {
            finish()
        }
    }

}
