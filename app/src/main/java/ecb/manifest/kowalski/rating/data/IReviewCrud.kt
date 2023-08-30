package ecb.manifest.kowalski.rating.data

import ecb.manifest.kowalski.rating.models.Review

interface IReviewCrud {
    fun addReview(serviceQuality: Int, fuelPrices: Double)

    fun findReviewById(id: Int): Review?

    fun updateReview(id: Int, newServiceQuality: Int, newFuelPrices: Double)

    fun removeReview(id: Int)

    fun listReviews(): List<Review>
}