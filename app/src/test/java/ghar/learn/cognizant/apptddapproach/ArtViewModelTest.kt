package ghar.learn.cognizant.apptddapproach

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import ghar.learn.cognizant.apptddapproach.repo.FakeArtRepositoryImpl
import ghar.learn.cognizant.apptddapproach.util.Status
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.AssertionError

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    /**
     * This testing approach will use 'Test-Doubles' whereby instead of
     * using actual functionality of repo; that has business logic (ArtRepositoryImpl) holder of this app
     * a copy (fake) or 'double' of the same will be used so actual backEnd-calls would not be needed
     * */
    private lateinit var viewModel : ArtViewModel

    @Before
    fun setup(){

        // Test Doubles for viewModel-Unit
        viewModel = ArtViewModel(FakeArtRepositoryImpl)

    }

    // ViewModel (Business-Logic bearer) functionality testing-STARTS
    @Test
    fun `insert art without name returns error`() {
        viewModel.makeArt("", "LLB_artist", "900")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()            // this callback 'getOrAwaitValueTest' converts liveData into 'regular' data
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artist returns error`() {
        viewModel.makeArt("LLB", "", "2000")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()            // this callback 'getOrAwaitValueTest' converts liveData into 'regular' data
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without year returns error`() {
        viewModel.makeArt("LLB", "LLB_artist", "")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()            // this callback 'getOrAwaitValueTest' converts liveData into 'regular' data
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    // ViewModel (Business-Logic bearer) functionality testing-ENDS

}