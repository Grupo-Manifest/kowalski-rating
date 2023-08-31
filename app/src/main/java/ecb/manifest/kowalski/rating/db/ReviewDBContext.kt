package ecb.manifest.kowalski.rating.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ecb.manifest.kowalski.rating.models.Review

@Database(
    entities = [Review::class],
    version = 1,
)
abstract class ReviewDBContext : RoomDatabase() {
    abstract fun getDAO(): ReviewDao

    companion object {
        private var databaseInstance: ReviewDBContext? = null

        fun getReviewDatabase(context: Context): ReviewDBContext {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder<ReviewDBContext>(
                    context.applicationContext, ReviewDBContext::class.java, "Reviews"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance!!
        }
    }
}
