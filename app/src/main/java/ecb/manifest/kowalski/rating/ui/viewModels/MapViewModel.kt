package ecb.manifest.kowalski.rating.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.rating.networking.HttpClient
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val httpClient = HttpClient()

    fun getGeoPoints(): List<GeoPoint> {
        var pointsList = listOf<GeoPoint>()
        val points = httpClient.run()

        points.thenAccept { responseBody ->
            responseBody?.let { body ->
                val json = JsonParser.parseString(body.string()) as JsonObject
                val locationsArray = json.getAsJsonArray("locations")

                for (element in locationsArray) {
                    val location = element as JsonObject
                    val lat = location.get("lat").asFloat
                    val lng = location.get("lng").asFloat
                    println("$lat ,$lng")
                }
            }
        }.exceptionally { error ->
            Log.e(TAG,"Error: ${error.message}")
            null
        }

        return pointsList
    }

    companion object {
        private const val TAG = "MapViewModel"
    }
}