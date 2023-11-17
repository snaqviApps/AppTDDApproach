package ghar.learn.cognizant.apptddapproach.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import ghar.learn.cognizant.apptddapproach.R
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
class ArtFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory : ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `test Navigation From ArtFragment to ArtDetailsFragment`() {
        val mockedNavController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),mockedNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
        Mockito.verify(mockedNavController).navigate(
            ArtsFragmentDirections.actionArtsFragmentToArtsDetailFragment()
        )
    }
}