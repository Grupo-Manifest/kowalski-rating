package ecb.manifest.kowalski.rating.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ecb.manifest.kowalski.rating.db.ReviewRepository
import ecb.manifest.kowalski.rating.events.ReviewEvent
import ecb.manifest.kowalski.rating.events.ReviewState
import ecb.manifest.kowalski.rating.models.Review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val repository: ReviewRepository
) : ViewModel() {
    private val _reviews = repository.getReviewsById()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ReviewState())
    val state = combine(_state, _reviews) { state, reviews ->
        state.copy(reviews = reviews)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ReviewState())

    fun onEvent(event: ReviewEvent) {
        when (event) {
            is ReviewEvent.DeleteReview -> {
                viewModelScope.launch {
                    repository.deleteReview(event.review)
                }
            }

            ReviewEvent.HideReviewDialog -> {
                _state.update { it.copy(
                    isWritingReview = false
                ) }
            }

            ReviewEvent.SaveReview -> {
                val serviceQuality = state.value.serviceQuality

                if (serviceQuality.isBlank()) {
                    return
                }

                val review = Review(
                    serviceQuality = serviceQuality,
                    latitude = 0f,
                    longitude = 0f,
                )

                viewModelScope.launch {
                    repository.upsertReview(review)
                }
                _state.update { it.copy(
                    isWritingReview = false
                ) }
            }

            is ReviewEvent.SetServiceQuality -> {

                _state.update { it.copy(
                    serviceQuality = event.serviceQuality
                ) }
            }

            ReviewEvent.ShowReviewDialog -> {
                _state.update { it.copy(
                    isWritingReview = true
                ) }
            }

            else -> {}
        }
    }
}