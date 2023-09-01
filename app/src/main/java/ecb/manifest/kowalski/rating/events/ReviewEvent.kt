package ecb.manifest.kowalski.rating.events

import ecb.manifest.kowalski.rating.models.Review

sealed interface ReviewEvent {
    object SaveReview: ReviewEvent
    data class SetServiceQuality(val serviceQuality: String): ReviewEvent
    object ShowReviewDialog: ReviewEvent
    object HideReviewDialog: ReviewEvent
    data class DeleteReview(val review: Review): ReviewEvent
}
