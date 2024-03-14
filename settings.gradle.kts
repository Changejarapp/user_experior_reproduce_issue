enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven("https://jitpack.io")
        maven("https://sdk.uxcam.com/android/")
        maven( "https://packages.bureau.id/api/packages/Bureau/maven")
        maven("https://plugins.gradle.org/m2/")
        maven("https://storage.googleapis.com/r8-releases/raw")
        maven("https://plotline-android-sdk.s3.amazonaws.com")
        maven("https://maven.juspay.in/jp-build-packages/hyper-sdk/")
        maven("https://artifactory.paytm.in/libs-release-local")
        gradlePluginPortal()
    }

    pluginManagement {
        repositories {
            gradlePluginPortal()
            mavenCentral()
            google()
            maven("https://maven.juspay.in/jp-build-packages/hyper-sdk/")
        }
    }
}
rootProject.name = "JarApp"
include("app")
include(":user-experior-sdk")

