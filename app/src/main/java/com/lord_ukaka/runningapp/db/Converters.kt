package com.lord_ukaka.runningapp.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    //Convert image from bitmap to bytearray, so that room can understand and store it.
    @TypeConverter
    fun fromBitmapToByteArray(bmp: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    //Convert image from byteArray to bitmap, so we can use it.
    @TypeConverter
    fun fromByteArrayToBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes,0, bytes.size)
    }
}