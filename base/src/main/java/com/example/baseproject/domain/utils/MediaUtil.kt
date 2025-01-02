package com.example.baseproject.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RawRes
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.offline.DownloadService.start
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.transform.CircleCropTransformation
import com.example.baseproject.domain.utils.gone
import com.example.baseproject.domain.utils.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

object MediaUtil {
    /**
     * Get real file path from URI.
     */
    fun getFilePathFromUri(
        context: Context,
        uri: Uri,
    ): String? {
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf(MediaStore.MediaColumns.DATA)
            context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                if (cursor.moveToFirst()) {
                    return cursor.getString(columnIndex)
                }
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    /**
     * Get metadata for media files (images, videos, audio).
     */
    fun getMediaMetadata(
        context: Context,
        uri: Uri,
    ): Map<String, String> {
        val metadataRetriever = MediaMetadataRetriever()
        val metadata = mutableMapOf<String, String>()

        try {
            metadataRetriever.setDataSource(context, uri)
            metadata["duration"] = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) ?: "0"
            metadata["width"] = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH) ?: "0"
            metadata["height"] = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT) ?: "0"
            metadata["mime_type"] = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE) ?: ""
        } catch (e: Exception) {
            Log.e("MediaUtil", "Error extracting metadata", e)
        } finally {
            metadataRetriever.release()
        }

        return metadata
    }

    /**
     * Compress image file.
     */
    suspend fun compressImage(
        file: File,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int = 80,
    ): File =
        withContext(Dispatchers.IO) {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(file.path, options)

            options.inJustDecodeBounds = false
            val bitmap = BitmapFactory.decodeFile(file.path, options)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, maxWidth, maxHeight, true)

            val compressedFile = File(file.parent, "compressed_${file.name}")
            FileOutputStream(compressedFile).use { fos ->
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos)
            }
            compressedFile
        }

    /**
     * Fix image orientation based on EXIF metadata.
     */
    fun fixImageOrientation(file: File): Bitmap {
        val exif = ExifInterface(file.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        val bitmap = BitmapFactory.decodeFile(file.path)
        val matrix = Matrix()

        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    /**
     * Validate file size and type.
     */
    fun validateFile(
        file: File,
        maxSizeInMB: Int,
        allowedTypes: List<String>,
    ): Boolean {
        val sizeInMB = file.length() / (1024 * 1024)
        val type = file.extension.lowercase()
        return sizeInMB <= maxSizeInMB && allowedTypes.contains(type)
    }

    /**
     * Play video using ExoPlayer.
     */
    fun playVideo(
        context: Context,
        videoUri: Uri,
        exoPlayer: ExoPlayer,
    ) {
        val mediaItem = MediaItem.fromUri(videoUri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    /**
     * Play raw video resource with VideoView.
     */
    fun playRawVideo(
        context: Context,
        @RawRes rawRes: Int,
        videoView: VideoView,
    ) {
        val uri = Uri.parse("android.resource://${context.packageName}/$rawRes")
        videoView.setVideoURI(uri)
        videoView.start()
    }

    /**
     * Pick media file from gallery.
     */
    fun pickMedia(mediaLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "*/*" // Allow all media types
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*", "audio/*"))
        mediaLauncher.launch(intent)
    }

    fun ImageView.loadImage(
        source: Any?,
        onSuccess: (() -> Unit)? = null,
        onFail: (() -> Unit)? = null,
        defaultImage: Any? = null,
    ) {
        try {
            this.load(source) {
                listener(
                    onStart = { request ->
                        crossfade(true)
                        placeholder(
                            CircularProgressDrawable(context).apply {
                                strokeWidth = 5f
                                centerRadius = 30f
                                start()
                            },
                        )
                        transformations(CircleCropTransformation())
                    },
                    onSuccess = { request, result ->
                        onSuccess?.invoke() ?: this@loadImage.visible()
                    },
                    onError = { request, result ->
                        onFail?.invoke()
                            ?: defaultImage?.let {
                                this@loadImage.load(defaultImage)
                            } ?: this@loadImage.gone()
                    },
                )
            }
        } catch (e: Exception) {
            onFail?.invoke()
                ?: defaultImage?.let {
                    this@loadImage.load(defaultImage)
                } ?: this@loadImage.gone()
        }
    }
}

/**
 * Example of usage:
 * - Get Real File Path: val filePath = MediaUtil.getFilePathFromUri(context, uri)
 * - Compress an Image: val compressedFile = MediaUtil.compressImage(File(filePath), maxWidth = 800, maxHeight = 800)
 * - Extract Metadata:
 * val metadata = MediaUtil.getMediaMetadata(context, uri)
 * println("Duration: ${metadata["duration"]}, MimeType: ${metadata["mime_type"]}")
 * - Fix Image Orientation: val rotatedBitmap = MediaUtil.fixImageOrientation(File(filePath))
 * - Play Video with ExoPlayer:
 * class MainActivity : AppCompatActivity() {
 *     private lateinit var exoPlayer: ExoPlayer
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_main)
 *
 *         val uri: Uri = ... // The URI of the video
 *         exoPlayer = ExoPlayer.Builder(this).build()
 *
 *         MediaUtil.playVideo(this, uri, exoPlayer)
 *     }
 *
 *     override fun onDestroy() {
 *         super.onDestroy()
 *         exoPlayer.release()
 *     }
 * }
 * - Pick Media:
 * class MainActivity : AppCompatActivity() {
 *     private lateinit var mediaPickerLauncher: ActivityResultLauncher<Intent>
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_main)
 *
 *         // Initialize the launcher
 *         mediaPickerLauncher = registerForActivityResult(
 *             ActivityResultContracts.StartActivityForResult()
 *         ) { result ->
 *             if (result.resultCode == Activity.RESULT_OK) {
 *                 val selectedMediaUri: Uri? = result.data?.data
 *                 // Handle the selected media URI
 *                 selectedMediaUri?.let {
 *                     Log.d("MediaUtil", "Selected media: $it")
 *                 }
 *             }
 *         }
 *
 *         // Example usage
 *         findViewById<Button>(R.id.pickMediaButton).setOnClickListener {
 *             MediaUtil.pickMedia(mediaPickerLauncher)
 *         }
 *     }
 * }
 */
