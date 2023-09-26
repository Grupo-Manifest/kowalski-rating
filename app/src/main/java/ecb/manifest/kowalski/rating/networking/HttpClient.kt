package ecb.manifest.kowalski.rating.networking

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class HttpClient {
    private val client = OkHttpClient()

    fun run() {
        val request = Request.Builder()
            .url("https://shellgsllocator.geoapp.me/api/v2/locations/nearest_to?lat=-23.555771&lng=-46.639557&limit=50&locale=pt_BR&format=json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) println("$name : $value")

                    println(response.body!!.string())
                }
            }
        })
    }
}