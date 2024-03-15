// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.cashsqldelight) apply false
    alias(libs.plugins.kmm.bridge) apply false
    alias(libs.plugins.moko.resources) apply false
    id("hypersdk.plugin") version "2.0.6" apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.androidDynamicFeature) apply false
}

buildscript {

    extra.apply {
        set("versionName", "6.9.3_RELEASE")
        set("versionCode", 483)

        set("minSdk", 21)
        set("targetSdk", 33)
        set("compileSdk", 33)
        set("javaVersion", JavaVersion.VERSION_1_8)
        set("jvmTarget", "1.8")

        set(
            "appDistributionReleaseNotes",
            "p2p navigation optimisation"
        )
    }

    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.juspay.in/jp-build-packages/hyper-sdk/")
        maven("https://plotline-android-sdk.s3.amazonaws.com")
        maven("https://packages.bureau.id/api/packages/Bureau/maven")
        maven("https://storage.googleapis.com/r8-releases/raw")
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.google.services)
        classpath(libs.firebase.appdistribution.gradle)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.hypersdk.asset.plugin)
        classpath(libs.build.konfig)
        classpath(libs.moko.resources.generator)
        classpath(libs.r8)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

/**This block of code runs after every new task is added.
 * If such task’s name starts with the word assembleProd
 * but it is not an AndroidTest kind of task, then we
 * execute uploadCrashlyticsSymbolFileProd for the build variant that the assembleProd task specified initially.
 *
 * NOTE: For Firebase Crashlytics NDK Reports we need to run this task for specified conditions.
 **/
tasks.whenTaskAdded {
    if (name.startsWith("assembleProd") && name != "assembleReleaseAndroidTest" && name != "assembleDebugAndroidTest") {
        finalizedBy("uploadCrashlyticsSymbolFile" + name.substring("assembleProd".length))
    }
}
