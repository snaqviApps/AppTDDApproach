package ghar.learn.cognizant.apptddapproach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ghar.learn.cognizant.apptddapproach.view.ArtFragmentFactory
import ghar.learn.kotlinreview.replaceMultipleSpaces
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var artFragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val str = "How   are you k"
        println("input string: $str")
        println("output string: ${str.replaceMultipleSpaces()}")

        supportFragmentManager.fragmentFactory = artFragmentFactory
        setContentView(R.layout.activity_main)
    }
}