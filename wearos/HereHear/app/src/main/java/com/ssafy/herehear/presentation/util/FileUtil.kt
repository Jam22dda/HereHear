package com.ssafy.herehear.presentation.util

import android.content.Context
import java.io.DataInputStream
import java.io.File

fun Context.readPersonalCodeFile(fileName: String): String {
    val file = File(filesDir, "personalCode.txt")
    if (!file.exists()) {
        val input = openFileInput("personalCode.txt")
        val dis = DataInputStream(input)
        val personalCode = dis.readUTF()
        dis.close() //종료

        return personalCode
    }
    return ""
}

fun Context.deletePersonalCodeFile(fileName: String) {
    val file = File(filesDir, "personalCode.txt")
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