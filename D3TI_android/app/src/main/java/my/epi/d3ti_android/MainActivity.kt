package my.epi.d3ti_android

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import io.github.controlwear.virtual.joystick.android.JoystickView
import my.epi.d3ti_android.Activity.StartActivity
import my.epi.d3ti_android.Utils.ErrorMessage
import my.epi.d3ti_android.web.VideoWebViewClient

class MainActivity : AppCompatActivity() {
    private lateinit var serverIp: String
    private lateinit var serverPort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val extras = intent.extras

        this.serverIp = if (null == extras?.getString("ip")) "192.168.60.42"
            else extras.getString("ip")!!

        this.serverPort = if (extras == null || !extras.containsKey("port")) "8081"
            else extras.getString("port")!!

//        val joystick = findViewById<View>(R.id.joystickViewLeft) as JoystickView
//        joystick.setOnMoveListener { angle, strength ->
//            // do whatever you want
//        }

        val buttonSettings = findViewById<ImageButton>(R.id.settingButton)
        buttonSettings.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }
        val buttonTerminal = findViewById<ImageButton>(R.id.terminalButton)
        buttonTerminal.setOnClickListener {
            // TODO
            ErrorMessage.show(this, "Work in progress")
        }

        this.createWebView()
    }

    private fun createWebView() {
        val myWebView = findViewById<WebView>(R.id.webview)
        val url = ""//this.getString(R.string.auth_url)
        myWebView?.loadUrl(url)
        myWebView?.settings?.allowContentAccess = true
        myWebView?.settings?.allowFileAccess = true
        myWebView?.settings?.javaScriptEnabled = true
        myWebView?.webViewClient = VideoWebViewClient(this)
    }
}