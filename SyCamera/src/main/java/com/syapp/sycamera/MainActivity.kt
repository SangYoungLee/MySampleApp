package com.syapp.sycamera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit

private const val REQUEST_CODE_PERMISSIONS = 10

// This is an array of all the permission specified in the manifest
private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewFinder = findViewById(R.id.view_finder)

        if (allPermissionGranted()) {
            viewFinder.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    private lateinit var viewFinder: TextureView

    private fun startCamera() {
        val previewConfig = PreviewConfig.Builder()
            .setTargetAspectRatio(Rational(1, 1))
            .setTargetResolution(Size(640, 640))
            .build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {

            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .setTargetAspectRatio(Rational(1, 1))
            .setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            .build()

        val imageCapture = ImageCapture(imageCaptureConfig)
        findViewById<View>(R.id.capture_button).setOnClickListener {
            val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
            imageCapture.takePicture(file, object: ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    val msg = "Photo capture succeeded: ${file.absolutePath}"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d("CameraXApp", msg)
                }

                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String,
                    cause: Throwable?
                ) {
                    val msg = "Photo capture failed: $message"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.e("CameraXApp", msg)
                    cause?.printStackTrace()
                }
            })
        }

        // Setup image analysis pipeline that computes average pixel luminance
        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            // Use a worker thread for image analysis to prevent glitches
            val analyzerThread = HandlerThread("LuminosityAnalysis").also { it.start() }
            setCallbackHandler(Handler(analyzerThread.looper))

            // In our analysis, we care more about the latest image than
            // analyzing *every* image
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()

        // Build the image analysis use case and instantiate our analyzer
        val analyzerUseCase = ImageAnalysis(analyzerConfig).apply {
            analyzer = LuminosityAnalyzer()
        }

        CameraX.bindToLifecycle(this, preview, imageCapture, analyzerUseCase)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees = when(viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        viewFinder.setTransform(matrix)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionGranted()) {
                viewFinder.post { startCamera() }
            } else {
                Toast.makeText(this, "Permission Not Granted By the user", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(applicationContext, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private class LuminosityAnalyzer : ImageAnalysis.Analyzer {

        private var lastAnalyzedTimestamp = 0L

        /**
         * Helper extension function used to extract a byte array from an
         * image plane buffer
         */
        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()    // rewind the buffer to zero
            val data = ByteArray(remaining())
            get(data)

            return data
        }

        override fun analyze(image: ImageProxy, rotationDegrees: Int) {
            val currentTimestamp = System.currentTimeMillis()

            // Calculate the average luma no more often than every second
            if (currentTimestamp - lastAnalyzedTimestamp > TimeUnit.SECONDS.toMillis(1)) {
                // Since format in ImageAnalysis is YUV, image.planes[0]
                // contains the Y (luminance) plane
                val buffer = image.planes[0].buffer

                // Extract image data from callback object
                val data = buffer.toByteArray()

                // Convert the data into an array of pixel values
                val pixels = data.map { it.toInt() and 0xff }

                // Compute average luminance for the image
                val luma = pixels.average()

                // Log the new luma value
                Log.d("SyCamera", "Average luminosity: $luma")

                // Update timestamp of last analyzed frame
                lastAnalyzedTimestamp = currentTimestamp
            }
        }
    }
}
