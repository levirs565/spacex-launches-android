plugins {
    id("com.android.application")
    kotlin("android")
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
}

val kotlin: String by rootProject.extra
val coreKtx: String by rootProject.extra
val appCompat: String by rootProject.extra
val material: String by rootProject.extra
val constraintLayout: String by rootProject.extra
val navigationFragment: String by rootProject.extra
val navigationUi: String by rootProject.extra
val junit: String by rootProject.extra
val extJunit: String by rootProject.extra
val espressoCore: String by rootProject.extra

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin)
    implementation(coreKtx)
    implementation(appCompat)
    implementation(material)
    implementation(constraintLayout)
    implementation(project(":core"))
    implementation(navigationFragment)
    implementation(navigationUi)
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
}