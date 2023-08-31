package ecb.manifest.kowalski.rating.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var serviceQuality: String = "1",
)
