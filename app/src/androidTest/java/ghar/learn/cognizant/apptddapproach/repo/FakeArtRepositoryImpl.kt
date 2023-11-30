package ghar.learn.cognizant.apptddapproach.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import ghar.learn.cognizant.apptddapproach.util.Resource

object FakeArtRepositoryImplInstrumentation : IArtRepository {

    private val arts = mutableListOf<Art>()
    private val artLiveData = MutableLiveData<List<Art>>(arts)              // for method 'getArt()'

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artLiveData
    }

    override suspend fun searchImage(imageName: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0, 0, listOf()))
    }

    private fun refreshData(){
        artLiveData.postValue(arts)
    }
}
