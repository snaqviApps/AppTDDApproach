package ghar.learn.cognizant.apptddapproach.repo

import androidx.lifecycle.LiveData
import ghar.learn.cognizant.apptddapproach.api.ImageApiService
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.db.ArtDAO
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import ghar.learn.cognizant.apptddapproach.util.Resource
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artDAO: ArtDAO,
    private val imageApiService : ImageApiService
)  : IArtRepository {
    override suspend fun insertArt(art: Art) {
        artDAO.insert(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDAO.delete(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDAO.observeArts()
    }

    override suspend fun searchImage(imageName: String): Resource<ImageResponse> {
        return try {
            val response = imageApiService.imageSearch(imageName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("body is null", null)
            } else {
                Resource.error("Response is null", null)
            }
        } catch (e : Exception){
            Resource.error("No Data with the error: $e", null)
        }
    }
}