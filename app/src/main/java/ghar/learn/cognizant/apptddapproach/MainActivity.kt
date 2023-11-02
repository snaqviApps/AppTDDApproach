package ghar.learn.cognizant.apptddapproach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ghar.learn.cognizant.apptddapproach.view.ArtFragmentFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var artFragmentFactory: ArtFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = artFragmentFactory
        setContentView(R.layout.activity_main)

        println("api-keyy: ${BuildConfig.API_KEY}")
    }
}