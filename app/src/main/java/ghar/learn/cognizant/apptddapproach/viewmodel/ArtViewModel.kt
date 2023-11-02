package ghar.learn.cognizant.apptddapproach.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import ghar.learn.cognizant.apptddapproach.repo.IArtRepository
import ghar.learn.cognizant.apptddapproach.util.Utils.Resource
//import ghar.learn.cognizant.apptddapproach.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val iArtRepository: IArtRepository
) : ViewModel() {

    // Art Fragment
    val arts = iArtRepository.getArt()

    // Art Api Fragment
    private val _images = MutableLiveData<Resource<ImageResponse>>()
    val images : LiveData<Resource<ImageResponse>> = _images
    private val _imageSelected = MutableLiveData<String>()
    val imageSelected : LiveData<String> = _imageSelected

    // Art Details Fragment
    private var _insertArtMessage = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>> = _insertArtMessage

    fun resetInsertArtMessage() {
        _insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun reSetSelectedImage(url: String){
        _imageSelected.postValue(url)
    }

    fun deleteArt(art : Art) = viewModelScope.launch {
        iArtRepository.deleteArt(art)
    }

    fun insertArt(art : Art) = viewModelScope.launch {
        iArtRepository.insertArt(art)
    }

    fun makeArt(name: String, artistName : String, year: String) {
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            _insertArtMessage.postValue(Resource.error("Enter name, artistName, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception){
            _insertArtMessage.postValue(Resource.error("Year needs to be a number", null))
            return
        }
        val art = Art(null, name, artistName, yearInt, _imageSelected.value ?: "")
        insertArt(art)
        reSetSelectedImage("")
        _insertArtMessage.postValue(Resource.success(art))
    }
    // Art Details Fragment ENDS

    fun searchImage(searchImageName : String) {
        if(searchImageName.isEmpty())  {
            return
        }

        _images.value = Resource.loading(null)              // pre-loading state
        viewModelScope.launch {
            val response = iArtRepository.searchImage(searchImageName)
            _images.value = response
        }
    }

}