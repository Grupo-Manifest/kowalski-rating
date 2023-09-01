package ecb.manifest.kowalski.rating.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ecb.manifest.kowalski.rating.db.ReviewDBContext
import ecb.manifest.kowalski.rating.db.ReviewDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ReviewDBContext =
        ReviewDBContext.getReviewDatabase(context)

    @Singleton
    @Provides
    fun provideDAO(reviewDatabase: ReviewDBContext): ReviewDao =
        reviewDatabase.getDAO()
}