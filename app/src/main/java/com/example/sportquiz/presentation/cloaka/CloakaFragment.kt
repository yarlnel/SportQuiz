package com.example.sportquiz.presentation.cloaka

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isGone
import com.example.sportquiz.databinding.FragmentCloakaBinding
import com.example.sportquiz.presentation.common.backpress.BackPressedStrategyOwner
import com.example.sportquiz.presentation.common.fragment.BaseFragment

class CloakaFragment : BaseFragment<FragmentCloakaBinding>(
    FragmentCloakaBinding::inflate
), BackPressedStrategyOwner {

    private lateinit var baseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseUrl = arguments?.getString(Arg.Url) ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()
        loadUrl(baseUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView(): Unit = with(binding.webView) {
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.javaScriptEnabled = true
        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.loadingIndicator.isGone = true
            }
        }
    }

    private fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
    }

    override fun handleBackPress() = with(binding.webView) {
        if (canGoBack()) {
            goBack()
        } else {
            requireActivity().finish()

        }
    }

    private object Arg {
        const val Url = "arg_url"
    }

    object Factory {

        fun newInstance(url: String) = CloakaFragment().apply {
            arguments = Bundle().apply {
                putString(Arg.Url, url)
            }
        }
    }
}