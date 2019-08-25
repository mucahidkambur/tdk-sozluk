package com.mucahitkambur.tdksozluk.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.util.EXTRA_URL
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context, url: String): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra(EXTRA_URL)
        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)

        toolbar_common.setNavigationOnClickListener {
            finish()
        }
    }

}
