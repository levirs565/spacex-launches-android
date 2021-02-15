plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
val kotlinCoroutineCore: String by rootProject.extra
val coreKtx: String by rootProject.extra
val paging: String by rootProject.extra
val roomRuntime: String by rootProject.extra
val roomKtx: String by rootProject.extra
val roomCompiler: String by rootProject.extra
val retrofit: String by rootProject.extra
val retrofitGson: String by rootProject.extra
val threetenbp: String by rootProject.extra
val daggerRuntime: String by rootProject.extra
val daggerCompiler: String by rootProject.extra
val junit: String by rootProject.extra
val extJunit: String by rootProject.extra
val espressoCore: String by rootProject.extra
val appCompat: String by rootProject.extra
val constraintLayout: String by rootProject.extra
val material: String by rootProject.extra
val coil: String by rootProject.extra
val sqlCipher: String by rootProject.extra
val sqlKtx: String by rootProject.extra

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(kotlin)
    api(kotlinCoroutineCore)
    api(coreKtx)
    api(paging)
    implementation(roomRuntime)
    implementation(roomKtx)
    kapt(roomCompiler)
    implementation(retrofit)
    implementation(retrofitGson)
    api(threetenbp)
    api(daggerRuntime)
    kapt(daggerCompiler)
    api(appCompat)
    api(constraintLayout)
    api(material)
    api(coil)
    implementation(sqlCipher)
    implementation(sqlKtx)
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
}
