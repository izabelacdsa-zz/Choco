package com.login.instrumented.testutils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit

private const val BODY_DELAY = 0L

fun MockWebServer.enqueue200Response(body: String) {
    enqueueResponse(body, 200)
}

fun MockWebServer.enqueueResponse(body: String, code: Int) {
    enqueue(MockResponse()
        .setResponseCode(code)
        .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
        .setBody(body))
}