package com.example.sportquiz.presentation.cloaka

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sportquiz.databinding.FragmentCloakaBinding
import com.example.sportquiz.presentation.common.backpress.BackPressedStrategyOwner
import com.example.sportquiz.presentation.common.fragment.BaseFragment

class CloakaFragment : BaseFragment<FragmentCloakaBinding>(
    FragmentCloakaBinding::inflate
), BackPressedStrategyOwner {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()
    }

    private fun setUpWebView(): Unit = with(binding.webView) {
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webViewClient = WebViewClient()
        loadFirstPage()
    }

    private fun loadFirstPage() {
        // TODO: Add work with config
        val cloakaHomePageUrl = "https://habr.com/ru/articles/279641"
        loadUrl(cloakaHomePageUrl)
    }

    private fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
    }

    override fun handleBackPress() = with(binding.webView) {
        if (canGoBack()) {
            goBack()
        } else {
            ultimateOnBackPressed()
        }
    }
}