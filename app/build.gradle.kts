import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.ReadOnlyProductFlavor
import org.gradle.internal.extensibility.DefaultExtraPropertiesExtension

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.appdistribution")
//    id("com.google.firebase.crashlytics")
    id("hypersdk.plugin")
//    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.kotlin.serialization)
}


android {
    compileSdk = rootProject.extra.get("compileSdk") as Int

    bundle {
        language {
            enableSplit = false
        }
    }

    defaultConfig {
        applicationId = "com.jar.app"

        minSdk = rootProject.extra.get("minSdk") as Int
        targetSdk = rootProject.extra.get("targetSdk") as Int

        versionCode = rootProject.extra.get("versionCode") as Int
        versionName = rootProject.extra.get("versionName") as String

        testInstrumentationRunner = "com.jar.app.core_tests.HiltTestRunner"

        configurations.all {
            resolutionStrategy {
                force("androidx.emoji2:emoji2:1.3.0")
                force("androidx.emoji2:emoji2-views-helper:1.3.0")
                force("co.touchlab:kermit:2.0.2")
                force("com.github.bumptech.glide:compiler:4.13.2")
//                exclude(group = "com.google.guava", module = "listenablefuture")
            }
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = rootProject.extra.get("javaVersion") as JavaVersion
        targetCompatibility = rootProject.extra.get("javaVersion") as JavaVersion
    }

    kotlinOptions {
        jvmTarget = rootProject.extra.get("jvmTarget") as String
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    namespace = "com.jar.app"

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.9")

    implementation(libs.mpAndroidChart)
    //FlexBox
    implementation(libs.flexbox)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.work.testing)
    androidTestImplementation(libs.androidx.core.testing)
    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    // Splash Screen
    implementation(libs.android.splash)

    //AppCompat + KTX
    implementation(libs.bundles.appcompat.ktx)
    implementation(libs.legacy.support.v4)

    //Material Component
    implementation(libs.material)

    //Constraint Layout
    implementation(libs.constraintlayout)

    //LiveData + ViewModel
    implementation(libs.bundles.lifecycle.livedata.viewmodel)
    implementation(libs.androidx.lifecycle.lifecycle.process)

    //RecyclerView
    implementation(libs.androidx.recyclerview)

    //Adapter Delegate

    //Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    //Fragment KTX
    implementation(libs.androidx.fragment.ktx)

    //Logger
    implementation(libs.timber)

    //Kotlin-Coroutines
    implementation(libs.bundles.coroutines)

    //Glide
    implementation(libs.glide2)
    implementation(libs.compose.glide)

    //TrueCaller SDK
//    implementation(libs.truecaller.sdk)

    //Dagger DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.fragment)

    // LeakCanary
//    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.8.1")
//    mockImplementation("com.squareup.leakcanary:leakcanary-android:2.8.1")

    //Country Code Picker
    implementation(libs.countrycodepicker)

    //Lottie
    implementation(libs.lottie)

    //Preferences
    implementation(libs.preference.ktx)

    //Swipe To Refresh
    implementation(libs.androidx.swiperefreshlayout)

    //Dynamic Permission
    implementation(libs.permissionsdispatcher)
    kapt(libs.permissionsdispatcher.processor)

    //Progress HUD
    implementation(libs.kprogresshud)

    //Bounce Effect
    implementation(libs.overscroll.decor.android)

    //Expandable View
    implementation(libs.expandableLayout)

    //EventBus
    implementation(libs.eventbus)

    //Paytm SDK
    implementation(libs.appinvokesdk)

    //CleverTap SDK
    implementation(libs.clevertap.android.sdk)
    implementation(libs.push.templates)

    

    //AppsFlyer SDK
    implementation(libs.appsflyer.af.android.sdk)

    //AppsFlyer LVL SDK
    implementation(libs.appsflyer.lvl)

    // Facebook Analytics SDK
    implementation(libs.facebook.android.sdk)
    //Facebook AppLinks
    implementation(libs.facebook.applinks)

    //Paging
    implementation(libs.androidx.paging.runtime.ktx)

    //Three Ten ABP - Date Parsing
    implementation(libs.threetenbp)

    //Custom Chrome Tabs
    implementation(libs.browser)


    //InstallReferrer
    implementation(libs.installreferrer)

//    //Firebase BOM
//    implementation(platform(libs.firebase.bom))
//
//    //Firebase
//    implementation(libs.bundles.firebase)

    // Play Services Ads
    //implementation(libs.gms.play.services.ads.lite)

    //Auto Read
//    implementation(libs.bundles.play.services.auth)

    //Phone Number Formatting
    implementation(libs.libphonenumber.android)

    //OTP View
    implementation(libs.android.otpview.pinview)

    //Room SQLite ORM
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.room.ktx)

    //Work Manager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.hilt.work)
    kapt(libs.androidx.hilt.hilt.compiler2)


    //Circular Progress Bar
    implementation(libs.mikhaellopez.circularprogressbar)

    //Mockk
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    //Truth assertion
    testImplementation(libs.truth)

    // Coroutine support in Play services [Tasks]
//    implementation(libs.kotlinx.coroutines.play.services)

    //Shimmer
    implementation(libs.shimmer)

    //PlotLine
    implementation(libs.plotline.android.sdk)

    //Rating Bar
    implementation(libs.simpleRatingBar)

    //Jetpack Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    //User Experior
    implementation(projects.userExperiorSdk)

    //Material Design
    implementation(libs.bundles.compose.material)

    // Android Studio Preview support
    implementation(libs.bundles.compose.ui.tooling)

    //Integration with activities
    implementation(libs.activity.compose)

    //Integration with LiveData
    implementation(libs.compose.runtime.livedata)
    //play-services-base
//    implementation(libs.gms.play.services.basement)

    //downloadable fonts
    implementation(libs.compose.ui.google.fonts)

    //Bureau SDK
    implementation(libs.corelib)

    //Epoxy
    implementation(libs.epoxy)
    kapt(libs.epoxy.processor) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-compiler-embeddable")
    }
}

hyperSdkPlugin {
    clientId.set("jar")
    sdkVersion.set("2.1.20")
}