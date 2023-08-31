package ecb.manifest.kowalski.rating.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ecb.manifest.kowalski.rating.models.Review

@Database(
    entities = [Review::class],
    version = 1,
)
abstract class ReviewDBContext : RoomDatabase() {
    abstract val dao: ReviewDao
}
