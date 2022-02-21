package my.epi.d3ti_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.controlwear.virtual.joystick.android.JoystickView
import my.epi.d3ti_android.Activity.StartActivity
import my.epi.d3ti_android.Utils.ErrorMessage
import my.epi.d3ti_android.web.VideoWebViewClient
import java.lang.Math.cos
import java.lang.Math.sin

class MainActivity : AppCompatActivity() {
    private lateinit var serverIp: String
    private lateinit var serverPort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configWebView()

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
        this.joysticksControl()
    }

    private fun joysticksControl()
    {
        // Control main motors
        val joystickLeft = findViewById<View>(R.id.joystickViewLeft) as JoystickView
        joystickLeft.setOnMoveListener { angle, strength ->
            val x = cos(angle.toDouble()) * (strength / 100) // -1; 1
            val y = sin(angle.toDouble()) * (strength / 100) // -1; 1

            val coefLeft: Double = if (x < 0) 1.0 else (1 - x)
            val coefRight: Double = if (x > 0) 1.0 else (-x)
            val throttleLeft = (y * 100) * coefLeft // (-100 to 100)
            val throttleRight = (y * 100) * coefRight // (-100 to 100)

            // TODO: api call
            println("Joystick left : angle ${angle}, strength $strength")
        }
        // Control turret servo motors
        val joystickRight = findViewById<View>(R.id.joystickViewRight) as JoystickView
        joystickRight.setOnMoveListener { angle, strength ->
            val x = cos(angle.toDouble()) * (strength / 100) // -1; 1
            val y = sin(angle.toDouble()) * (strength / 100) // -1; 1

            val angleX = x * 90 + 90 // (0 to 180)
            val angleY = y * 90 + 90 // (0 to 180)

            // TODO: api call
            println("Joystick Right : angle ${angle}, strength $strength")
        }
    }

    private fun configWebView()
    {
        val extras = intent.extras

        this.serverIp = if (null == extras?.getString("ip")) this.getString(R.string.def_server_ip)
        else extras.getString("ip")!!
        this.serverPort = if (extras == null || !extras.containsKey("port")) this.getString(R.string.def_server_port)
        else extras.getString("port")!!
        if (extras != null && extras?.containsKey("ip")) {
            this.createWebView()
        }
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