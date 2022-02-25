package my.epi.d3ti_android.web;

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import my.epi.d3ti_android.MainActivity
import my.epi.d3ti_android.Utils.ErrorMessage

class API(
    private val activity: MainActivity,
    val url: String
    ) {
    val queue = Volley.newRequestQueue(activity)
    val TAG = "API"

    fun stop() {
        queue.cancelAll(TAG)
    }

    private fun sendPostRequestWithBody(endpoint: String, getParams: () -> Map<String, String>?) {
        val request: StringRequest = object : StringRequest(
            Method.POST,
            "$url$endpoint",
            { response ->
                println("API $endpoint ok: $response")
                activity.setConnectionStatus(true)
            },
            { error ->
                ErrorMessage.show(activity, "Network error $endpoint : ${error.message}")
                println("API $endpoint: An error occurred : $error")
                activity.setConnectionStatus(false)
            }
        ) {
            override fun getBodyContentType(): String? {
                return "application/json; charset=utf-8"
            }
            override fun getParams(): Map<String, String>? {
                return getParams()
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
                println("API $endpoint ok: $response")
                activity.setConnectionStatus(true)
            },
            { error ->
                ErrorMessage.show(activity, "Network error: ${error.message}")
                println("API $endpoint: An error occurred : $error")
                activity.setConnectionStatus(false)
            }
        )
        request.tag = TAG
        queue.add(request)
    }

    fun postTurretPosition(position1: Int, position2: Int) {
        fun getParams(): Map<String, String>? {
            val params: MutableMap<String, String> = HashMap()
            params["position1"] = position1.toString()
            params["position2"] = position2.toString()
            return params
        }
        this.sendPostRequestWithBody("/turret/position", { getParams() })
    }

    fun postTurretFire() {
        this.sendPostRequest("/turret/fire")
    }

    fun postWheelMovement(throttle1: Int, throttle2: Int) {
        fun getParams(): Map<String, String>? {
            val params: MutableMap<String, String> = HashMap()
            params["throttle1"] = throttle1.toString()
            params["throttle2"] = throttle2.toString()
            return params
        }
        this.sendPostRequestWithBody("/wheel/movement", { getParams() })
    }

    fun postBuzzer() {
       this.sendPostRequest("/audio/buzzer")
    }

    fun postServiceCamera(status: Boolean) {
        fun getParams(): Map<String, String>? {
            val params: MutableMap<String, String> = HashMap()
            params["status"] = status.toString()
            return params
        }
        this.sendPostRequestWithBody("/camera", { getParams() })
    }

    fun postServiceCollision(status: Boolean) {
        fun getParams(): Map<String, String>? {
            val params: MutableMap<String, String> = HashMap()
            params["status"] = status.toString()
            return params
        }
        this.sendPostRequestWithBody("/collision", { getParams() })
    }

}
