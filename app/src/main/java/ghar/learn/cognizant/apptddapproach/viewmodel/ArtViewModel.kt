package ghar.learn.cognizant.apptddapproach.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.model.ImageResponse
import ghar.learn.cognizant.apptddapproach.repo.IArtRepository
import ghar.learn.cognizant.apptddapproach.util.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
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

    private val _selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String> = _selectedImage

    // Art Details Fragment
      /**
       * Below approach Lines# 33-34 does not work as the the property needs
       * to be used in resetInsertArtMsg() method,
       * which does not give expected results (it goes back to ArtsFragment-screen when pressed 'fab'-CTA
       * AFTER saving first entry in ArtRecycleAdapter [at: @ArtsFragment.kt# 58 - 63 ]
       * */
//    private val _insertArtMessage = MutableLiveData<Resource<Art>>()
//    val insertArtMessage : LiveData<Resource<Art>> = _insertArtMessage
    private var _insertArtMessage = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = _insertArtMessage

    fun resetInsertArtMsg() {
        _insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url: String){
        _selectedImage.postValue(url)
    }

    fun deleteArt(art : Art) = viewModelScope.launch {
        iArtRepository.deleteArt(art)
    }

    fun insertArt(art : Art) = viewModelScope.launch {
        iArtRepository.insertArt(art)
    }

fun makeArt(name : String, artistName : String, year : String) {
    if (name.isEmpty() || artistName.isEmpty() || year.isEmpty() ) {
        _insertArtMessage.postValue(Resource.error("Enter name, artist, year", null))
        return
    }

    val yearInt = try {
        year.toInt()
    } catch (e: Exception) {
        _insertArtMessage.postValue(Resource.error("Year should be number",null))
        return
    }

    val art = Art(name, artistName, yearInt,_selectedImage.value?: "")
    insertArt(art)
    setSelectedImage("")
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