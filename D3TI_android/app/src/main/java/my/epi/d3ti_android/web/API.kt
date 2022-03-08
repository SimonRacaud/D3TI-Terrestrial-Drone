package my.epi.d3ti_android.web;

import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import my.epi.d3ti_android.MainActivity
import my.epi.d3ti_android.Utils.ErrorMessage
import java.io.UnsupportedEncodingException


class API(
    private val activity: MainActivity,
    val url: String
    ) {
    val queue = Volley.newRequestQueue(activity)
    val TAG = "API"

    fun stop() {
        queue.cancelAll(TAG)
    }

    private fun sendPostRequestWithBody(endpoint: String, body: String?) {
        val request: StringRequest = object : StringRequest(
            Method.POST,
            "$url$endpoint",
            { response ->
                activity.setConnectionStatus(true)
            },
            { error ->
                MainActivity.terminalFragment.pushElement("Network error $endpoint : ${error.message}")
                println("API $endpoint: An error occurred : $error")
                activity.setConnectionStatus(false)
            }
        ) {
            override fun getBodyContentType(): String? {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray? {
                return try {
                    body?.toByteArray(Charsets.UTF_8)
                } catch (uee: UnsupportedEncodingException) {
                    VolleyLog.wtf(
                        "Unsupported Encoding while trying to get the bytes of %s using %s",
                        body,
                        "utf-8"
                    )
                    null
                }
            }
        }
        request.tag = TAG
        queue.add(request)
    }

    private fun sendPostRequest(endpoint: String) {
        val request = StringRequest(
            Request.Method.POST,
            "$url$endpoint",
            { response ->
                activity.setConnectionStatus(true)
            },
            { error ->
                MainActivity.terminalFragment.pushElement("Network error $endpoint : ${error.message}")
                println("API $endpoint: An error occurred : $error")
                activity.setConnectionStatus(false)
            }
        )
        request.tag = TAG
        queue.add(request)
    }

    fun postTurretPosition(position1: Int, position2: Int) {
        this.sendPostRequestWithBody("/turret/position", "{\n" +
                "\t\"position1\": $position1,\n" +
                "\t\"position2\": $position2\n" +
                "}")
    }

    fun postTurretFire() {
        this.sendPostRequest("/turret/fire")
    }

    fun postWheelMovement(throttle1: Int, throttle2: Int) {
        this.sendPostRequestWithBody("/wheel/movement", "{\n" +
                "\t\"throttle1\": $throttle1,\n" +
                "\t\"throttle2\": $throttle2\n" +
                "}")
    }

    fun postBuzzer() {
       this.sendPostRequest("/audio/buzzer")
    }

    fun postServiceCamera(status: Boolean) {
        this.sendPostRequestWithBody("/camera", "{\n" +
                "\t\"status\": $status\n" +
                "}")
    }

    fun postServiceCollision(status: Boolean) {
        fun getParams(): Map<String, String>? {
            val params: MutableMap<String, String> = HashMap()
            params["status"] = status.toString()
            return params
        }
        this.sendPostRequestWithBody("/collision", "{\n" +
                "\t\"status\": $status\n" +
                "}")
    }

}
