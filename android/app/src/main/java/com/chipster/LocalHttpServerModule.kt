package com.chipster

import android.util.Log
import com.facebook.react.bridge.*
import fi.iki.elonen.NanoHTTPD
import java.io.IOException

class LocalHttpServerModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private val server = SimpleHttpServer(8356)

    override fun getName(): String {
        return "LocalHttpServer"
    }

    @ReactMethod
    fun startServer(promise: Promise) {
        try {
            server.start()
            Log.d("LocalHttpServer", "Server started at http://localhost:8356/")
            promise.resolve("Server started on port 8356")
        } catch (e: IOException) {
            promise.reject("START_FAILED", "Could not start server", e)
        }
    }

    @ReactMethod
    fun stopServer(promise: Promise) {
        server.stop()
        Log.d("LocalHttpServer", "Server stopped.")
        promise.resolve("Server stopped")
    }

    private class SimpleHttpServer(port: Int) : NanoHTTPD("0.0.0.0", port) {
        override fun serve(session: IHTTPSession): Response {
            return newFixedLengthResponse(
                Response.Status.OK,
                "application/json",
                """{"message": "Hello from Kotlin HTTP Server!"}"""
            )
        }
    }
}
