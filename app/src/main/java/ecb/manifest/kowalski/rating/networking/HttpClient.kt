package ecb.manifest.kowalski.rating.networking

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.util.concurrent.CompletableFuture


class HttpClient {
    private val client = OkHttpClient()

    fun run(): CompletableFuture<ResponseBody?> {
        val responseBody = CompletableFuture<ResponseBody?>()

        val request = Request.Builder()
            .url("https://shellgsllocator.geoapp.me/api/v2/locations/nearest_to?lat=-23.555771&lng=-46.639557&limit=50&locale=pt_BR&format=json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseBody.completeExceptionally(e)
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        responseBody.completeExceptionally(IOException("Unexpected code $response"))
                    }
                    responseBody.complete(response.body!!)
                }
            }
        })

        return responseBody
    }
}