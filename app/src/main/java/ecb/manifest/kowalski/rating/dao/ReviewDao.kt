package ecb.manifest.kowalski.rating.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ecb.manifest.kowalski.rating.models.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Upsert
    suspend fun upsertReview(review: Review)

    @Delete
    suspend fun deleteReview(review: Review)

    @Query("SELECT * FROM review ORDER BY id ASC")
    fun getReviewsById(): Flow<List<Review>>
}