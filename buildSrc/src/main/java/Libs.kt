object Libs {
    // kotlin STD lib
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    //kotlin coroutines
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
    // Android Jetpack
    const val appCompat = "androidx.appcompat:appcompat:${Versions.androidXCore}"
    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val architectureComponentsExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycleVersion}"
    const val navigationCore = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    // Android Jetpack UI
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"
    // Dependency Injection
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerLib = "com.google.dagger:dagger:${Versions.dagger}"
    const val koinCore = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    // Rx
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid2}"
    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Logging}"
    //ui
    const val circleImage = "com.mikhaellopez:circularimageview:${Versions.circleImage}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    // permissions
    const val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermissions}"
    // Location
    const val googleLocation =
        "com.google.android.gms:play-services-location:${Versions.googleLocation}"
    // maps
    const val googleMaps= "com.google.android.gms:play-services-maps:${Versions.googleMaps}"
    //Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val androidXTest = "androidx.test:runner:${Versions.androidXTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin2 =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin2}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoInLine = "org.mockito:mockito-inline:${Versions.mockitoInLine}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
}
