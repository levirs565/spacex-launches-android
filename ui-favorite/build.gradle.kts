plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
}
android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.levirs.spacexlaunches.ui.favorite"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

val kotlin: String by rootProject.extra
val coreKtx: String by rootProject.extra
val appCompat: String by rootProject.extra
val paging: String by rootProject.extra
val navigationFragment: String by rootProject.extra
val navigationUi: String by rootProject.extra
val junit: String by rootProject.extra
val extJunit: String by rootProject.extra
val espressoCore: String by rootProject.extra

dependencies {
    implementation(project(":core"))
    implementation(kotlin)
    implementation(coreKtx)
    implementation(appCompat)
    implementation(paging)
    implementation(navigationFragment)
    implementation(navigationUi)
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
}