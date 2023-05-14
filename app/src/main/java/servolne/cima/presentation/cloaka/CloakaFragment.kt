package servolne.cima.presentation.cloaka

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isGone
import servolne.cima.databinding.FragmentCloakaBinding
import servolne.cima.presentation.common.backpress.BackPressedStrategyOwner
import servolne.cima.presentation.common.fragment.BaseFragment

class CloakaFragment : BaseFragment<FragmentCloakaBinding>(
    FragmentCloakaBinding::inflate
), BackPressedStrategyOwner {

    private lateinit var baseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseUrl = arguments?.getString(Arg.Url) ?: return
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState ?: return
        binding.webView.restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        binding.webView.saveState(bundle)
        outState.putBundle("webViewState", bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()
        if (savedInstanceState != null) {
            Toast.makeText(requireContext(), "Inited", Toast.LENGTH_LONG).show()
            restoreWebViewState(savedInstanceState.getBundle("webViewState")!!)
        } else {
            loadUrl(baseUrl)
        }
    }

    private fun restoreWebViewState(bundle: Bundle) {
        binding.webView.restoreState(bundle)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView(): Unit = with(binding.webView) {
        with(settings) {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            databaseEnabled = true
            setSupportZoom(true)
            allowFileAccess = true
            allowContentAccess = true
        }
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