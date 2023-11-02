package ghar.learn.cognizant.apptddapproach
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TddApproachApplication : Application() {
//class TddApproachApplication  : MultiDexApplication() {

}