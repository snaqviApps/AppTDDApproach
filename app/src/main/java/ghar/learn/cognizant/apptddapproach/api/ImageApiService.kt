package ghar.learn.cognizant.apptddapproach.api

import ghar.learn.cognizant.apptddapproach.BuildConfig.API_KEY
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String = API_KEY
    ) : Response<ImageResponse>
}