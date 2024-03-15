import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.ReadOnlyProductFlavor
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import org.gradle.internal.extensibility.DefaultExtraPropertiesExtension

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("hypersdk.plugin")
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.kotlin.serialization)
}


android {
    compileSdk = rootProject.extra.get("compileSdk") as Int

    signingConfigs {
        named("debug") {
            storeFile = file("../debugcert")
            storePassword = "ChangeJar@Save$"
            keyAlias = "changejardebug"
            keyPassword = "ChangeJar"
        }
    }

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

        buildConfigField("String", "UX_CAM_KEY", "\"cshbbuxtt8aip09\"")

        configurations.all {
            resolutionStrategy {
                force("androidx.emoji2:emoji2:1.3.0")
                force("androidx.emoji2:emoji2-views-helper:1.3.0")
                force("co.touchlab:kermit:2.0.2")
                force("com.github.bumptech.glide:compiler:4.13.2")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("Boolean", "IS_PLAY_STORE", "true")
            buildConfigField("Boolean", "MOCK", "false")
            lintOptions {
                disable("MissingTranslation")
                disable("ExtraTranslation")
            }
            isTestCoverageEnabled = false

            // Extension for firebase NDK crash report.
            configure<CrashlyticsExtension> {
                nativeSymbolUploadEnabled = true
                strippedNativeLibsDir = "build/intermediates/stripped_native_libs/release/out/lib"
                unstrippedNativeLibsDir = "build/intermediates/merged_native_libs/release/out/lib"
            }
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("Boolean", "IS_PLAY_STORE", "false")
            buildConfigField("Boolean", "MOCK", "false")
            buildConfigField("Boolean", "DEBUG", "true")
            isDebuggable = true
            isTestCoverageEnabled = true
        }
        create("mock") {
            signingConfig = signingConfigs.getByName("mock")
            buildConfigField("Boolean", "IS_PLAY_STORE", "false")
            buildConfigField("Boolean", "MOCK", "true")
            buildConfigField("Boolean", "DEBUG", "true")
            isDebuggable = true
            isTestCoverageEnabled = false
        }
    }

    flavorDimensions.add("config")
    productFlavors {
        create("prod") {
            dimension = "config"
            applicationId = "com.jar.app"
            buildConfigField("String", "PAYTM_MID", "\"CHANGE32137276398543\"")
            buildConfigField(
                "String",
                "PAYTM_CALLBACK_URL",
                "\"https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=\""
            )
            buildConfigField(
                "String",
                "PAYTM_PAYMENT_URL",
                "\"https://securegw.paytm.in/theia/api/v1/showPaymentPage\""
            )
            buildConfigField(
                "String",
                "BASE_URL_SMS_PARSER",
                "\"https://webhook.myjar.app/production/\""
            )
            buildConfigField(
                "String",
                "BUREAU_AUTH_CLIENT_ID",
                "\"2623ca06-95e7-4d12-a5ff-9b4f98e7eab7\""
            )
            buildConfigField(
                "String",
                "BUREAU_CALLBACK_URL",
                "\"https://prod.myjar.app/v1/callback/otl/status\""
            )
            buildConfigField("String", "PHONEPE_PACKAGE", "\"com.phonepe.app\"")
            buildConfigField("String", "AUSPICIOUS_TIME_TOPIC", "\"auspiciousTimeAlertsTopic\"")
            resValue("string", "CLEVERTAP_ACCOUNT_ID", "W84-655-7R6Z")
            resValue("string", "CLEVERTAP_ACCOUNT_TOKEN", "556-4b0")
            resValue("string", "CLEVERTAP_XIAOMI_APP_ID", "2882303761519927429")
            val trueCallerKey by extra {
                mapOf(
                    "debug" to "tWvZT89efd251ca844b68a1557b28fbaf9594",
                    "mock" to "tWvZT89efd251ca844b68a1557b28fbaf9594",
                    "release" to "ZD4wr666c4798b2584a169c8350ce861c9a39"
                )
            }
            manifestPlaceholders["templateId"] = "/6H9Q"
            manifestPlaceholders["oneLinkUrl1"] = "jar.onelink.me"
            manifestPlaceholders["oneLinkUrl2"] = "click.myjar.app"
            manifestPlaceholders["oneLinkUrl3"] = "start.myjar.app"

            firebaseAppDistribution {
                releaseNotes = rootProject.extra.get("appDistributionReleaseNotes") as String
                serviceCredentialsFile = "$rootDir/changejarprod_app_distribution.json"
                groups = "teamjar"
            }
        }
        create("prodReplica") {
            dimension = "config"
            applicationId = "com.jar.app.replica"
            buildConfigField("String", "PAYTM_MID", "\"CHANGE32137276398543\"")
            buildConfigField(
                "String",
                "PAYTM_CALLBACK_URL",
                "\"https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=\""
            )
            buildConfigField(
                "String",
                "PAYTM_PAYMENT_URL",
                "\"https://securegw.paytm.in/theia/api/v1/showPaymentPage\""
            )
            buildConfigField(
                "String",
                "BASE_URL_SMS_PARSER",
                "\"https://webhook.myjar.app/prod-replica/\""
            )
            buildConfigField(
                "String",
                "BUREAU_AUTH_CLIENT_ID",
                "\"2623ca06-95e7-4d12-a5ff-9b4f98e7eab7\""
            )
            buildConfigField(
                "String",
                "BUREAU_CALLBACK_URL",
                "\"https://prod-replica.myjar.app/v1/callback/otl/status\""
            )
            buildConfigField("String", "PHONEPE_PACKAGE", "\"com.phonepe.app.preprod\"")
            buildConfigField("String", "AUSPICIOUS_TIME_TOPIC", "\"auspiciousTimeAlertsReplica\"")
            resValue("string", "CLEVERTAP_ACCOUNT_ID", "W84-655-7R6Z")
            resValue("string", "CLEVERTAP_ACCOUNT_TOKEN", "556-4b0")
            val trueCallerKey by extra {
                mapOf(
                    "debug" to "QpSVJ64e66ad11abf4eda9754399e82845bf4",
                    "mock" to "r4WZGa844413a431b4c8d92e298c031e52f43",
                    "release" to "jxnzTf3b23103e890411c8252fd2dbeaea1b5"
                )
            }

            manifestPlaceholders["templateId"] = "/6H9Q"
            manifestPlaceholders["oneLinkUrl1"] = "jar.onelink.me"
            manifestPlaceholders["oneLinkUrl2"] = "click.myjar.app"
            manifestPlaceholders["oneLinkUrl3"] = "start.myjar.app"

            firebaseAppDistribution {
                releaseNotes = rootProject.extra.get("appDistributionReleaseNotes") as String
                serviceCredentialsFile = "$rootDir/changejarprod_app_distribution.json"
                groups = "teamjar"
            }
        }
        create("staging") {
            dimension = "config"
            applicationId = "com.aso_centric.jar.staging"
            buildConfigField("String", "PAYTM_MID", "\"CHANGE11588127832171\"")
            buildConfigField(
                "String",
                "PAYTM_CALLBACK_URL",
                "\"https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=\""
            )
            buildConfigField(
                "String",
                "PAYTM_PAYMENT_URL",
                "\"https://securegw-stage.paytm.in/theia/api/v1/showPaymentPage\""
            )
            buildConfigField(
                "String",
                "BASE_URL_SMS_PARSER",
                "\"https://webhook.myjar.app/staging/\""
            )
            buildConfigField(
                "String",
                "BUREAU_AUTH_CLIENT_ID",
                "\"4e44dbc4-a7b7-4979-abac-64ea9db511e2\""
            )
            buildConfigField(
                "String",
                "BUREAU_CALLBACK_URL",
                "\"https://dev.myjar.app/v1/callback/otl/status\""
            )
            buildConfigField("String", "PHONEPE_PACKAGE", "\"com.phonepe.app.preprod\"")
            buildConfigField("String", "AUSPICIOUS_TIME_TOPIC", "\"auspiciousTimeAlertsReplica\"")
            resValue("string", "CLEVERTAP_ACCOUNT_ID", "TEST-Z84-655-7R6Z")
            resValue("string", "CLEVERTAP_ACCOUNT_TOKEN", "TEST-556-4b1")
            val trueCallerKey by extra {
                mapOf(
                    "debug" to "X5HEd6bb15d250c9440e39b123c174b467c84",
                    "mock" to "oj02uf8c0288afe7449d89930f2f3bca433df",
                    "release" to "VmdX6b52cab8a5b93487ba7977b0914df63f4"
                )
            }

            firebaseAppDistribution {
                releaseNotes = rootProject.extra.get("appDistributionReleaseNotes") as String
                serviceCredentialsFile = "$rootDir/changejarstaging_app_distribution.json"
                groups = "teamjar"
            }
            manifestPlaceholders["templateId"] = "/iZH6"
            manifestPlaceholders["oneLinkUrl1"] = "jar-staging.onelink.me"
            manifestPlaceholders["oneLinkUrl2"] = "jar-staging.onelink.me"
            manifestPlaceholders["oneLinkUrl3"] = "jar-staging.onelink.me"
        }
    }


    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            val flavor = variant.productFlavors[0] as ReadOnlyProductFlavor
            val extra = flavor.getProperty("ext") as DefaultExtraPropertiesExtension
            val map = extra.get("trueCallerKey") as Map<*, *>
            val value = map[variant.buildType.name] as String
            variant.resValue("string", "TRUECALLER_APP_KEY", value)
        }

    })


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


    sourceSets {

        // non-instrumental test example
        getByName("test") {
            //java.srcDir(project(":core-tests").file("src/test/java"))
        }
        // instrumental test example
        getByName("androidTest") {
            //java.srcDir(project(":core-tests").file("src/androidTest/java"))
        }

        getByName("mock") {
            assets {
                srcDirs("src/main/assetsDebug")
            }
        }
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
    }

    packagingOptions {
        resources {
            merges.add("values-**/*")
            merges.add("values/*")
            merges.add("drawable/ic_arrow_back.xml")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    namespace = "com.jar.app"

    kapt {
        correctErrorTypes = true
    }
}

dependencies {


    val mockImplementation by configurations

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.9")






















//    implementation(libs.core.preferences)











    //Core Web Pdf Viewer






































































    //Feature Daily Investment Tempering



















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

    //Firebase BOM
    implementation(platform(libs.firebase.bom))

    //Firebase
    implementation(libs.bundles.firebase)

    // Play Services Ads
    //implementation(libs.gms.play.services.ads.lite)

    //Auto Read
    implementation(libs.bundles.play.services.auth)

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
    implementation(libs.kotlinx.coroutines.play.services)

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
    implementation(libs.gms.play.services.basement)

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

configurations {
    all {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }
}

hyperSdkPlugin {
    clientId.set("jar")
    sdkVersion.set("2.1.20")
}