package ghar.learn.cognizant.apptddapproach.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule =  HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")                          // makes sure that it is getting from test-dataBase injection
    lateinit var testDatabase : ArtDatabase

    private lateinit var dao: ArtDAO

    @Before
    fun setup(){

         /**
          * replaced with Injected database from TestAppModule above
            database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
             ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
          */

        hiltRule.inject()                           // using Hilt-Injection
        dao = testDatabase.artDAO()
    }

    @After
    fun teardown(){
        testDatabase.close()
    }

    @Test
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