package com.login.instrumented.testutils

import androidx.test.platform.app.InstrumentationRegistry
import java.io.*
import java.lang.Exception

object AssetsLoader {
    fun loadAsset(assetName: String): String {
        return InstrumentationRegistry
            .getInstrumentation()
            .context
            .assets
            .open(assetName)
            .readContent()
    }

    private fun InputStream.readContent(): String {

        var content = ""
        try {
            val inputStreamReader = InputStreamReader(this)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString = bufferedReader.readLine()

            while (receiveString != null) {
                content += receiveString
                receiveString = bufferedReader.readLine()
            }
            this.close()

        } catch (e: Exception) {
        }
        return content
    }
}