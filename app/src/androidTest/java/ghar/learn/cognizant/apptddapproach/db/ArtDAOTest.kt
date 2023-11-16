package ghar.learn.cognizant.apptddapproach.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@ExperimentalCoroutinesApi
class ArtDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArtDatabase
    private lateinit var dao: ArtDAO

    @Before
    fun setup(){
         database = Room.inMemoryDatabaseBuilder(                                    // inMemoryDatabaseBuilder() callback is Only used for 'testing'-domain
            ApplicationProvider.getApplicationContext(),
             ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.artDAO()

    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
//    fun `insert art into database`() = runBlockingTest {      // Deprecated
    fun `insert art into database`() = runTest {
        val testArt = Art("Monara", "ABC", 1700, "testing.ghar", 1)
        dao.insert(testArt)
        val testArtList = dao.observeArts().getOrAwaitValue()
        assertThat(testArtList.contains(testArt))
    }

    @Test
    fun `delete art from database`() = runTest {
        val testArt = Art("Monara", "ABC", 1700, "testing.ghar", 1)
        dao.insert(testArt)
        dao.delete(testArt)

        val testArtList = dao.observeArts().getOrAwaitValue()
        assertThat(testArtList).doesNotContain(testArt)
    }

}