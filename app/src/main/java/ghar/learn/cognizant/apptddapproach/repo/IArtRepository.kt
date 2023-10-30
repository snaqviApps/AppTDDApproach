package ghar.learn.cognizant.apptddapproach.repo

import androidx.lifecycle.LiveData
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import ghar.learn.cognizant.apptddapproach.util.Resource

interface IArtRepository {

    // Local database associated methods START
    suspend fun insertArt(art : Art)
    suspend fun deleteArt(art : Art)
    fun getArt() : LiveData<List<Art>>
    // Local database associated methods END Here

    suspend fun searchImage(imageName : String) : Resource<ImageResponse>
}