package com.ssafy.herehear.presentation.util

import android.content.Context
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

fun Context.readPersonalCodeFile(fileName: String): String {
    val file = File(filesDir, fileName)
    if (file.exists()) {
        try {
            // 버퍼를 사용하여 파일의 내용을 한 번에 읽습니다.
            BufferedReader(InputStreamReader(openFileInput(fileName))).use { br ->
                // 파일의 모든 텍스트를 읽고 문자열로 반환합니다.
                return br.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } else {
        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return ""
}

fun Context.deletePersonalCodeFile(fileName: String) {
    val file = File(filesDir, fileName)
    if (file.exists()) {
        file.delete()
    }
}

fun Context.writePersonalCodeFile(filaName: String, personalCode: String) {
    val file = File(filesDir, "personalCode.txt")
    if (!file.exists()) {
        val output = openFileOutput("personalCode.txt", Context.MODE_PRIVATE)
        output.write(personalCode.toByteArray())
        output.close()
    }
}