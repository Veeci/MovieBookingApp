// NowPlayingWidgetService.kt
package com.example.moviebooking.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.moviebooking.R
import com.example.moviebooking.domain.usecases.movies.nowPlayingList.FetchNowPlayingMoviesUseCase
import com.example.moviebooking.data.local.entities.MovieItemEntity
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class NowPlayingWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return NowPlayingRemoteViewsFactory(applicationContext)
    }
}

class NowPlayingRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase by inject(FetchNowPlayingMoviesUseCase::class.java)
    private var movieList: List<MovieItemEntity> = emptyList()

    override fun onCreate() {
        // Initialization if needed
    }

    override fun onDataSetChanged() {
        // Fetch data from the API or local database
        runBlocking {
            fetchNowPlayingMoviesUseCase.fetchData(1).collect { response ->
                if (response is ResponseStatus.Success) {
                    movieList = response.data.results?.take(5)?.map { movie ->
                        MovieItemEntity(
                            id = movie?.id.toString(),
                            title = movie?.title,
                            releaseDate = movie?.releaseDate
                        )
                    } ?: emptyList()
                }
            }
        }
    }

    override fun onDestroy() {
        // Cleanup if needed
    }

    override fun getCount(): Int = movieList.size

    @SuppressLint("RemoteViewLayout")
    override fun getViewAt(position: Int): RemoteViews {
        val movie = movieList[position]
        val views = RemoteViews(context.packageName, R.layout.now_playing_widget_item)
        views.setTextViewText(R.id.widget_item_title, movie.title)
        views.setTextViewText(R.id.widget_item_released_date, movie.releaseDate)

        // Set up fill-in intent for item click
        val fillInIntent = Intent()
        fillInIntent.putExtra("movie_id", movie.id)
        views.setOnClickFillInIntent(R.id.widget_item_container, fillInIntent)

        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = movieList[position].id.toLong()

    override fun hasStableIds(): Boolean = true
}
