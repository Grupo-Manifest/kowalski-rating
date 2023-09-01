package ecb.manifest.kowalski.rating.events

import ecb.manifest.kowalski.rating.models.Review

data class ReviewState(
    val reviews: List<Review> = emptyList(),
    val serviceQuality: String = "",
    val isWritingReview: Boolean = false,
)
