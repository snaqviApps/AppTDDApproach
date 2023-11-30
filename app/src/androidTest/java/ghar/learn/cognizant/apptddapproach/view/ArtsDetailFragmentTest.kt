package ghar.learn.cognizant.apptddapproach.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.db.Art
import ghar.learn.cognizant.apptddapproach.db.getOrAwaitValue
import ghar.learn.cognizant.apptddapproach.repo.FakeArtRepositoryImplInstrumentation
import ghar.learn.cognizant.apptddapproach.tasks.launchFragmentInHiltContainer
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtsDetailFragmentTest {

    private lateinit var mockNavController: NavController

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
        mockNavController = Mockito.mock(NavController::class.java)
    }

    @Test
    fun `test Navigation From ArtDetailsFragment to ApiFragment`() {
        launchFragmentInHiltContainer<ArtsDetailFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockNavController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.artImageView)).perform(ViewActions.click())
        Mockito.verify(mockNavController).navigate(
            ArtsDetailFragmentDirections.actionArtsDetailFragmentToImageApiFragment()
        )
    }

    @Test
    fun `test press back`() {
        launchFragmentInHiltContainer<ArtsDetailFragment> (
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockNavController)
        }
        Espresso.pressBack()
        Mockito.verify(mockNavController).popBackStack()
    }

    @Test
    fun `test Database saved by reading as list`() {
        val testViewModel = ArtViewModel(FakeArtRepositoryImplInstrumentation)
        launchFragmentInHiltContainer<ArtsDetailFragment> (
            factory = fragmentFactory
        ) {
            viewModel = testViewModel
        }
        onView(withId(R.id.etArtName)).perform(replaceText("Sample-Art"))
        onView(withId( R.id.etArtistName)).perform(replaceText("SampleAris"))
        onView(withId( R.id.etArtYear)).perform(replaceText("16181"))
        onView(withId( R.id.save_button)).perform(click())
        assertTrue(
            testViewModel.arts.getOrAwaitValue().contains(
                Art(
                    "Sample-Art",
                    "Sample-Artist",
                    0,
                    "dummy-url")
            ).toString()
        , true)
    }

}