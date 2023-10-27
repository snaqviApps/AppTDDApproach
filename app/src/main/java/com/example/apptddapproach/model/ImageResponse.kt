package com.example.apptddapproach.model

data class ImageResponse(
    val total : Int,
    val totalHits : Int,
    val hits : List<ImageData>
) {
}