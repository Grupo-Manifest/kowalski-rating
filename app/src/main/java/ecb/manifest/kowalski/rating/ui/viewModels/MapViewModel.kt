package ecb.manifest.kowalski.rating.ui.viewModels

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.rating.networking.HttpClient
import org.osmdroid.util.GeoPoint
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val httpClient = HttpClient()

    fun getGeoPoints(): CompletableFuture<List<GeoPoint>> {
        val points = httpClient.run()

        return (points.thenApply { responseBody ->
            val pointsList = mutableListOf<GeoPoint>()

            responseBody?.let { body ->
                val json = JsonParser.parseString(body.string()) as JsonObject
                val locationsArray = json.getAsJsonArray("locations")

                for (element in locationsArray) {
                    val location = element as JsonObject
                    val latitude = location.get("lat").asDouble
                    val longitude = location.get("lng").asDouble

                    val point = GeoPoint(latitude, longitude)
                    pointsList.add(point)
                }
            }
            pointsList
        } ?: emptyList<GeoPoint>()) as CompletableFuture<List<GeoPoint>>
    }
}