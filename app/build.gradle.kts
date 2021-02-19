plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.levirs.spacexlaunches"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                "proguard-rules.pro"
            )
            signingConfig = getByName("debug").signingConfig
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures = mutableSetOf(":favorite")
}

val navigationFragment: String by rootProject.extra
val navigationUi: String by rootProject.extra
val navigationDynamicFeature: String by rootProject.extra
val recyclerView: String by rootProject.extra
val daggerCompiler: String by rootProject.extra
val junit: String by rootProject.extra
val extJunit: String by rootProject.extra
val espressoCore: String by rootProject.extra
val leakCanary: String by rootProject.extra
val glide: String by rootProject.extra
val glideCompiler: String by rootProject.extra

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(":core"))
    api(navigationFragment)
    api(navigationUi)
    implementation(navigationDynamicFeature)
    implementation(recyclerView)
    kapt(daggerCompiler)
    implementation(glide)
    kapt(glideCompiler)
    debugImplementation(leakCanary)
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
}
