package my.epi.d3ti_android.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import my.epi.d3ti_android.MainActivity
import my.epi.d3ti_android.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val serverIpInput = findViewById<EditText>(R.id.server_ip)
        val serverPortInput = findViewById<EditText>(R.id.serverPort)

        serverIpInput.setText("192.168.42.42")
        serverPortInput.setText("8081")

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            val serverIp: String = serverIpInput.text.toString()
            val serverPort = serverPortInput.text.toString()

            println("Hello "+  serverIp + serverPort)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ip", serverIp)
            intent.putExtra("port", serverPort)
            startActivity(intent)
        }
    }
}