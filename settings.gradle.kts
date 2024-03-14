enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
val githubProperties = java.util.Properties()
githubProperties.load(java.io.FileInputStream("$rootDir/github.properties"))

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
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Changejarapp/REPOSITORY_NAME_HERE") // Replace REPOSITORY_NAME_HERE with actual GitHub repo name
            credentials {
                username = githubProperties.getProperty("gprUser") ?: System.getenv("GPR_USER")
                password = githubProperties.getProperty("gprKey") ?: System.getenv("GPR_API_KEY")
            }
        }
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

