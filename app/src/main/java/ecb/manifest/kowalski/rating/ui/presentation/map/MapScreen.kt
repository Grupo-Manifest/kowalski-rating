package ecb.manifest.kowalski.rating.ui.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val initialLocation = GeoPoint(-23.57309181123147, -46.623389398907634)
        val cameraState = rememberCameraState {
            geoPoint = initialLocation
            zoom = 17.0
        }

        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState
        )
    }
}