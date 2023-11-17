package ghar.learn.cognizant.apptddapproach.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.tasks.launchFragmentInHiltContainer
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
        launchFragmentInHiltContainer<ArtsDetailFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockNavController)
        }
        Espresso.pressBack()
        Mockito.verify(mockNavController).popBackStack()

    }

}