package my.epi.d3ti_android.web

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import my.epi.d3ti_android.MainActivity
import my.epi.d3ti_android.Utils.ErrorMessage

/**
 * Auth page - web client
 */
internal class VideoWebViewClient(val context: MainActivity) : WebViewClient() {

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)

        ErrorMessage.show(
            context,
            error.toString()
        ) {
            context.finish()
        }
    }
}
