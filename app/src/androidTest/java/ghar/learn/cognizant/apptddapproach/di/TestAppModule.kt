package ghar.learn.cognizant.apptddapproach.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ghar.learn.cognizant.apptddapproach.db.ArtDatabase
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoomDatabase(): ArtDatabase {
       return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}