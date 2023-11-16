plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}


android {
    namespace = "ghar.learn.cognizant.apptddapproach"
    compileSdk = 34

    defaultConfig {
        applicationId = "ghar.learn.cognizant.apptddapproach"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "ghar.learn.cognizant.apptddapproach.HiltTestRunner"    // using custom-Test-Runner

        buildConfigField("String", "API_KEY", "${properties["API_KEY_VALUE"]}")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation("androidx.test:runner:1.5.2")
    val archVersion = "2.2.0"
    val coroutineVersion = "1.7.3"
    val roomVersion = "2.6.0"
    val daggerVersion = "2.48"
    val lifecycleVersion = "2.6.2"
    val truthVersion = "1.1.4"
    val navigationVersion = "2.7.4"
    val glideVersion="4.16.0"
    val fragmentTestingVersion = "1.6.2"

    // common libraries
    implementation(project(":kotlinreview"))                     // from module: kotlinreview

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Dependency Injection:Hilt
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    ksp("com.google.dagger:hilt-compiler:$daggerVersion")
    ksp("androidx.hilt:hilt-compiler:1.1.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    //lifeCycle, viewModel, liveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.activity:activity-ktx:1.8.0")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    //Room
//    kapt("androidx.room:room-compiler:$room_version")         // kapt is being replaced with ksp
    // optional - Kotlin Extensions and Coroutines support for Room

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    ksp("com.github.bumptech.glide:ksp:4.14.2")


    // TestImplementations
    implementation("androidx.test:core:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("androidx.arch.core:core-testing:$archVersion")
    testImplementation("org.robolectric:robolectric:4.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    testImplementation("org.mockito:mockito-core:4.7.0")

    // Android Test Implementations

            //    androidTestImplementation "junit:junit:4.13.2"    ------> groovy-syntax
            //    androidTestImplementation("junit:junit:4.13.2")   ------> Kotlin-syntax
    androidTestImplementation("junit", "junit","4.13.2")    // kotlin-equivalent-syntax (to above)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.mockito:mockito-android:4.7.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("com.google.truth:truth:$truthVersion")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito:mockito-core:4.7.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.43.2")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:$daggerVersion")

    debugImplementation("androidx.fragment:fragment-testing:$fragmentTestingVersion")

    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1") {
//            exclude group : "org.checkerframework", module : "checker"    ----> groovy-syntax
        exclude("org.checkerframework",  "checker")
    }


}