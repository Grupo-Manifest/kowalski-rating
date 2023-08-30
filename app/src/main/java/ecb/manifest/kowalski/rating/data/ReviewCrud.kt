package ecb.manifest.kowalski.rating.data

import ecb.manifest.kowalski.rating.models.Review

class ReviewCrud : IReviewCrud {
    val reviewList = mutableListOf<Review>()
    var currentId = 1

    override fun addReview(serviceQuality: Int, fuelPrices: Double) {
        val newReview = Review(currentId++, serviceQuality, fuelPrices)
        reviewList.add(newReview)
    }

    override fun findReviewById(id: Int): Review? {
        return reviewList.find { it.id == id }
    }

    override fun updateReview(id: Int, newServiceQuality: Int, newFuelPrices: Double) {
        val review = findReviewById(id)
        review?.let {
            it.serviceQuality = newServiceQuality
            it.fuelPrices = newFuelPrices
        }
    }

    override fun removeReview(id: Int) {
        reviewList.removeIf { it.id == id }
    }

    override fun listReviews(): List<Review> {
        return reviewList.toList()
    }
}