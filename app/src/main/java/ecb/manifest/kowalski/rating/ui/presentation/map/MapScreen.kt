package ecb.manifest.kowalski.rating.ui.presentation.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import ecb.manifest.kowalski.rating.ui.viewModels.MapViewModel
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen(
    viewModel: MapViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val initialLocation = GeoPoint(-23.57309181123147, -46.623389398907634)
        val cameraState = rememberCameraState {
            geoPoint = initialLocation
            zoom = 17.0
        }

        val futurePointsList = viewModel.getGeoPoints()
        val geoPointsList = mutableListOf<GeoPoint>()
        futurePointsList.thenApply { pointsList ->
            for (point in pointsList) {
                geoPointsList.add(point)
            }
        }.join()

        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState,
        ) {
            Log.d("MVM","$")
            for (point in geoPointsList) {
                val markerState = rememberMarkerState(geoPoint = point)
                Marker(state = markerState)
            }
        }
    }
}