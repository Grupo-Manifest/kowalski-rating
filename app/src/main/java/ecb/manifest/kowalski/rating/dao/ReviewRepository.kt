package ecb.manifest.kowalski.rating.dao

import ecb.manifest.kowalski.rating.models.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val dao: ReviewDao) {
    suspend fun upsertReview(review: Review) = dao.upsertReview(review)
    suspend fun deleteReview(review: Review) = dao.deleteReview(review)
    fun getReviewsById(): Flow<List<Review>> = dao.getReviewsById()
}