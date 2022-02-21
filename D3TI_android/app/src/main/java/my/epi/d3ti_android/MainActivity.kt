package my.epi.d3ti_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import io.github.controlwear.virtual.joystick.android.JoystickView
import my.epi.d3ti_android.Activity.StartActivity
import my.epi.d3ti_android.Fragment.TerminalFragment
import my.epi.d3ti_android.Utils.ErrorMessage
import my.epi.d3ti_android.web.VideoWebViewClient
import java.lang.Math.cos
import java.lang.Math.sin

class MainActivity : AppCompatActivity() {
    private lateinit var serverIp: String
    private lateinit var serverPort: String
    private var terminalFragment: TerminalFragment = TerminalFragment()
    private var showTerminal: Boolean = false

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
        this.createWebView()
        this.joysticksControl()
        this.showTerminal(savedInstanceState)

        val buttonActionAlert = findViewById<Button>(R.id.alertButton)
        val buttonActionFire = findViewById<Button>(R.id.fireButton)
        buttonActionAlert.setOnClickListener {
            terminalFragment.pushElement("Buzzer: trigger")
            // TODO:
        }
        buttonActionFire.setOnClickListener {
            terminalFragment.pushElement("Turret: fire")
            // TODO
        }
    }

    private fun showTerminal(savedInstanceState: Bundle?)
    {
        val buttonTerminal = findViewById<ImageButton>(R.id.terminalButton)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.terminal_container_view, terminalFragment).commit()
            supportFragmentManager.beginTransaction().detach(terminalFragment).commit()
        }
        buttonTerminal.setOnClickListener {
            if (!showTerminal) {
                supportFragmentManager.beginTransaction().attach(terminalFragment).commit()
            } else {
                supportFragmentManager.beginTransaction().detach(terminalFragment).commit()
            }
            showTerminal = !showTerminal
        }
    }

    private fun joysticksControl()
    {
        // Control main motors
        val joystickLeft = findViewById<View>(R.id.joystickViewLeft) as JoystickView
        joystickLeft.setOnMoveListener { angle, strength ->
            val radian: Double = 0.0174533 * angle.toDouble()
            var strengthDb: Double = strength.toDouble()
            val x: Double = kotlin.math.cos(radian) * (strengthDb / 100.0) // -1; 1
            val y: Double = kotlin.math.sin(radian) * (strengthDb / 100.0) // -1; 1

            val coefLeft: Double = if (x < 0) 1.0 else (1 - x);
            val coefRight: Double = if (x > 0) 1.0 else (1 + x);
            if (y < 0)
                strengthDb = -strengthDb
            val throttleLeft: Int = (strengthDb * coefLeft).toInt() // (-100 to 100)
            val throttleRight: Int = (strengthDb * coefRight).toInt() // (-100 to 100)

            // TODO: api call
            terminalFragment.pushElement("Motor: left $throttleLeft right $throttleRight")
        }
        // Control turret servo motors
        val joystickRight = findViewById<View>(R.id.joystickViewRight) as JoystickView
        joystickRight.setOnMoveListener { angle, strength ->
            var strengthDb: Double = strength.toDouble()
            val radian: Double = 0.0174533 * angle.toDouble()
            val x = kotlin.math.cos(radian) * (strengthDb / 100.0) // -1; 1
            val y = kotlin.math.sin(radian) * (strengthDb / 100.0) // -1; 1

            val angleX = (x * 90 + 90).toInt() // (0 to 180)
            val angleY = (y * 90 + 90).toInt() // (0 to 180)

            // TODO: api call
            terminalFragment.pushElement("Turret: X $angleX Y $angleY")
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