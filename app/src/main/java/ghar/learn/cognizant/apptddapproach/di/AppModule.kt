package ghar.learn.cognizant.apptddapproach.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ghar.learn.cognizant.apptddapproach.api.ImageApiService
import ghar.learn.cognizant.apptddapproach.db.ArtDatabase
import ghar.learn.cognizant.apptddapproach.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//@InstallIn(ApplicationComponent::class)       ---> does not exist
@InstallIn(SingletonComponent::class)
object AppModule {

    /** Room Provider */
    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ArtDatabase::class.java,
        "ArtDB"
    ).build()

    /** DAO - Room-database */
    @Singleton
    @Provides
    fun injectDAO(database: ArtDatabase) = database.artDAO()

    /** backEnd Api provider*/
    @Singleton
    @Provides
    fun injectImageApi() : ImageApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ImageApiService::class.java)
    }


}

