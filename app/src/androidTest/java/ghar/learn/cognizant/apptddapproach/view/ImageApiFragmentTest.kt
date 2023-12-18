package ghar.learn.cognizant.apptddapproach.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.repo.FakeArtRepositoryImplInstrumentation
import ghar.learn.cognizant.apptddapproach.tasks.launchFragmentInHiltContainer
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

import com.google.common.truth.Truth.assertThat
import ghar.learn.cognizant.apptddapproach.db.getOrAwaitValue

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun `select image recyclerView ApiFragment`() {

        val mockNavController = Mockito.mock(NavController::class.java)
        val dummyImageUrl = "atilsamancioglu.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryImplInstrumentation)
        launchFragmentInHiltContainer<ImageApiFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockNavController)
            viewModel = testViewModel
            imageRecyclerAdapter.images = listOf(dummyImageUrl)
        }

        Espresso.onView(withId(R.id.recyclerViewImageSearch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageViewHolder>
                (0, click())
            )
        Mockito.verify(mockNavController).popBackStack()
        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(dummyImageUrl)

    }

    @Test
    fun `select negative image recyclerView ApiFragment`() {

        val mockNavController = Mockito.mock(NavController::class.java)
        val dummyImageUrl = "atilsamancioglu.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryImplInstrumentation)
        launchFragmentInHiltContainer<ImageApiFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockNavController)
            viewModel = testViewModel
            imageRecyclerAdapter.images = listOf(dummyImageUrl)
        }

        Espresso.onView(withId(R.id.recyclerViewImageSearch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageViewHolder>
                (0, click())
            )
        Mockito.verify(mockNavController).popBackStack()
        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isNotEqualTo(dummyImageUrl)

    }

}