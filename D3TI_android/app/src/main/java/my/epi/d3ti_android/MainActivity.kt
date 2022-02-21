package my.epi.d3ti_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import my.epi.d3ti_android.Activity.StartActivity
import my.epi.d3ti_android.Utils.ErrorMessage
import my.epi.d3ti_android.web.VideoWebViewClient

class MainActivity : AppCompatActivity() {
    private lateinit var serverIp: String
    private lateinit var serverPort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras
        this.serverIp = if (null == extras?.getString("ip")) this.getString(R.string.def_server_ip)
            else extras.getString("ip")!!
        this.serverPort = if (extras == null || !extras.containsKey("port")) this.getString(R.string.def_server_port)
            else extras.getString("port")!!
        if (extras != null && extras?.containsKey("ip")) {
            this.createWebView()
        }

//        val joystick = findViewById<View>(R.id.joystickViewLeft) as JoystickView
//        joystick.setOnMoveListener { angle, strength ->
//            // do whatever you want
//        }

        val buttonSettings = findViewById<ImageButton>(R.id.settingButton)
        buttonSettings.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            intent.putExtra("ip", serverIp)
            intent.putExtra("port", serverPort)
            startActivity(intent)
        }
        val buttonTerminal = findViewById<ImageButton>(R.id.terminalButton)
        buttonTerminal.setOnClickListener {
            // TODO
            ErrorMessage.show(this, "Work in progress")
        }
        this.createWebView()
    }

    private fun setConnectionStatus(connected: Boolean)
    {
        val circle = findViewById<View>(R.id.viewConnection)
        val text = findViewById<TextView>(R.id.textViewConnection)

        if (connected) {
            circle.setBackgroundResource(R.drawable.circle_green)
            text.setText(R.string.connected)
        } else {
            circle.setBackgroundResource(R.drawable.circle_red)
            text.setText(R.string.disconnected)
        }
    }

    private fun createWebView() {
        val myWebView = findViewById<WebView>(R.id.webview)
        val url = "http://"+this.serverIp+":"+this.serverPort
        myWebView?.loadUrl(url)
        myWebView?.settings?.allowContentAccess = true
        myWebView?.settings?.allowFileAccess = true
        myWebView?.settings?.javaScriptEnabled = true
        myWebView?.webViewClient = VideoWebViewClient(this)
    }
}