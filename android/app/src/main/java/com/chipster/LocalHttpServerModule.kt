package com.chipster

import android.os.Environment
import android.util.Log
import com.facebook.react.bridge.*
import fi.iki.elonen.NanoHTTPD
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class LocalHttpServerModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private val server = SimpleHttpServer(8356, reactContext)

    override fun getName(): String {
        return "LocalHttpServer"
    }

    @ReactMethod
    fun startServer(promise: Promise) {
        try {
            server.start()
            Log.d("LocalHttpServer", "🔥 Server started at http://0.0.0.0:8356/")
            promise.resolve("Server started on port 8356")
        } catch (e: IOException) {
            promise.reject("START_FAILED", "Could not start server", e)
        }
    }

    @ReactMethod
    fun stopServer(promise: Promise) {
        server.stop()
        Log.d("LocalHttpServer", "🛑 Server stopped.")
        promise.resolve("Server stopped")
    }

    private class SimpleHttpServer(port: Int, reactContext: ReactApplicationContext) : NanoHTTPD("0.0.0.0", port) {
        private val baseDirectory = reactContext.getExternalFilesDir(null)?.absolutePath ?: Environment.getExternalStorageDirectory().absolutePath + "/chipsterwebs"

        init {
            Log.d("LocalHttpServer", "📂 Base Directory: $baseDirectory")
        }

        override fun serve(session: IHTTPSession): Response {
            Log.d("LocalHttpServer", "📥 Incoming request: ${session.uri}")

            val uri = session.uri.trimStart('/')

            // Extract domain-based routing
            val pathSegments = uri.split("/")
            if (pathSegments.isEmpty()) {
                return newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/plain", "Invalid Request")
            }

            val domain = pathSegments[0] // First part of the path (e.g., "chipster", "newone")
            val relativePath = pathSegments.drop(1).joinToString("/") // Rest of the path

            val requestedFile = File("$baseDirectory/$domain/$relativePath")

            Log.d("LocalHttpServer", "📂 Requested: $requestedFile")

            return if (requestedFile.exists() && requestedFile.isFile) {
                try {
                    val fileInputStream = FileInputStream(requestedFile)
                    newChunkedResponse(Response.Status.OK, getMimeType(requestedFile.absolutePath), fileInputStream)
                } catch (e: IOException) {
                    newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "Error reading file")
                }
            } else {
                newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404 Not Found")
            }
        }

        private fun getMimeType(path: String): String {
            return when {
                path.endsWith(".html", ignoreCase = true) -> "text/html"
                path.endsWith(".css", ignoreCase = true) -> "text/css"
                path.endsWith(".js", ignoreCase = true) -> "application/javascript"
                path.endsWith(".png", ignoreCase = true) -> "image/png"
                path.endsWith(".jpg", ignoreCase = true) || path.endsWith(".jpeg", ignoreCase = true) -> "image/jpeg"
                path.endsWith(".gif", ignoreCase = true) -> "image/gif"
                path.endsWith(".mp3", ignoreCase = true) -> "audio/mpeg"
                path.endsWith(".wav", ignoreCase = true) -> "audio/wav"
                path.endsWith(".mp4", ignoreCase = true) -> "video/mp4"
                path.endsWith(".avi", ignoreCase = true) -> "video/x-msvideo"
                path.endsWith(".pdf", ignoreCase = true) -> "application/pdf"
                path.endsWith(".zip", ignoreCase = true) -> "application/zip"
                else -> "application/octet-stream"
            }
        }
    }
}
