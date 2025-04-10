package com.example.moviebooking.presentation.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.WorkManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.ExistingWorkPolicy
import com.example.moviebooking.data.local.entities.MovieItemEntity
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.R
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WidgetUpdateWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase by inject()

    override suspend fun doWork(): Result {
        try {
            val response = fetchNowPlayingMoviesUseCase.fetchData(page = 1).first()

            if (response is ResponseStatus.Success) {
                val movieList = response.data.results?.map { remote ->
                    MovieItemEntity(
                        id = remote?.id.toString(),
                        title = remote?.title,
                        posterPath = remote?.posterPath ?: "",
                        overview = remote?.overview ?: "",
                        voteAverage = remote?.voteAverage ?: 0.0
                    )
                }

                if (movieList != null) {
                    fetchNowPlayingMoviesUseCase.saveData(movieList).first()
                }

                val appWidgetManager = AppWidgetManager.getInstance(context)
                val widgetComponent = ComponentName(context, NowPLayingWidget::class.java)
                val widgetIds = appWidgetManager.getAppWidgetIds(widgetComponent)
                appWidgetManager.notifyAppWidgetViewDataChanged(widgetIds, R.id.widgetList)
            }

            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }

    companion object {
        fun enqueue(context: Context) {
            val request = OneTimeWorkRequestBuilder<WidgetUpdateWorker>().build()
            WorkManager.getInstance(context).enqueueUniqueWork(
                "update_now_playing_widget",
                ExistingWorkPolicy.REPLACE,
                request
            )
        }
    }
}
