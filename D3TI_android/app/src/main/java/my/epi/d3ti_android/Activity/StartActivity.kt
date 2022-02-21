package my.epi.d3ti_android.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import my.epi.d3ti_android.MainActivity
import my.epi.d3ti_android.R

class StartActivity : AppCompatActivity() {
    private lateinit var serverIp: String
    private lateinit var serverPort: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val extras = intent.extras
        this.serverIp = if (null == extras?.getString("ip")) this.getString(R.string.def_server_ip)
            else extras.getString("ip")!!
        this.serverPort = if (extras == null || !extras.containsKey("port")) this.getString(R.string.def_server_port)
            else extras.getString("port")!!

        val serverIpInput = findViewById<EditText>(R.id.server_ip)
        val serverPortInput = findViewById<EditText>(R.id.serverPort)
        serverIpInput.setText(this.serverIp)
        serverPortInput.setText(this.serverPort)

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            val serverIp: String = serverIpInput.text.toString()
            val serverPort = serverPortInput.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ip", serverIp)
            intent.putExtra("port", serverPort)
            startActivity(intent)
        }
    }
}