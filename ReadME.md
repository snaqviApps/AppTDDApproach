branch: add_navigation_n_viewbinding 
this branch is keeping the 'Room' library implementaion

branch: remoteCall_and_dependencyInjectionHilt
        keeps the implementation of
        1. Retrofit for backEnd calls
           with fetching data from http://pixabay.com, docs: https://pixabay.com/api/docs/
        2. Hilt: this is for Dependency Injection
        3. moved api-key to gradle.properties file 

branch: final changes
       1. changed module 'kotlinreview' to Type:library in kotlinreview/app/build.gradle.kts
       2. consumed one method of above-mentioned library in module:app (file: MainActivity# 20)