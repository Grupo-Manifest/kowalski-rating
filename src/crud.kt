data class Review(
    val id: Int,
    var serviceQuality: Int,
    var fuelPrices: Double
)

class ReviewCrud {
    val reviewList = mutableListOf<Review>()
    var currentId = 1

    fun addReview(
        serviceQuality: Int,
        fuelPrices: Double
    ) {
        reviewList.add(Review(currentId++, serviceQuality, fuelPrices))
    }

    fun findReviewById(id: Int): Review? {
        return reviewList.find { it.id == id }
    }

    fun updateReview(
        id: Int,
        newServiceQuality: Int,
        newFuelPrices: Double
    ) {
        val review = findReviewById(id)
        review?.let {
            it.serviceQuality = newServiceQuality
            it.fuelPrices = newFuelPrices
        }
    }

    fun removeReview(id: Int) {
        reviewList.removeIf { it.id == id }
    }

    fun listReviews(): List<Review> {
        return reviewList.toList()
    }
}

fun main() {
    val reviewCrud = ReviewCrud()
}
