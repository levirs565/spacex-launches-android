val kotlinVersion = "1.4.21"
val kotlinCoroutineVersion = "1.4.2"

val coreKtxVersion = "1.3.2"
val appCompatVersion = "1.2.0"
val materialVersion = "1.2.1"
val constraintLayoutVersion = "2.0.4"
val junitVersionVersion = "4.13.1"
val espressoCoreVersion = "3.3.0"
val extJunitVersion = "1.1.2"

val retrofitVersion = "2.9.0"
val threetenbpVersion = "1.5.0"

val pagingVersion = "3.0.0-alpha11"
val roomVersion = "2.3.0-alpha04"

val daggerVersion = "2.30.1"

val navigationVersion = "2.3.2"
val recyclerViewVersion = "1.1.0"
val lifecycleVersion = "2.2.0"
val coilVersion = "1.1.0"
val sqlCipherVersion = "4.4.2"
val sqlKtxVersion = "2.1.0"
val leakCanaryVersion = "2.6"

val kotlin by extra("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
val kotlinPlugin by extra(
    "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
)
val kotlinCoroutineCore by extra(
    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion"
)

val coreKtx by extra("androidx.core:core-ktx:$coreKtxVersion")
val appCompat by extra("androidx.appcompat:appcompat:$appCompatVersion")
val material by extra("com.google.android.material:material:$materialVersion")
val constraintLayout by extra("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
val junit by extra("junit:junit:$junitVersionVersion")
val extJunit by extra("androidx.test.ext:junit:$extJunitVersion")
val espressoCore by extra("androidx.test.espresso:espresso-core:$espressoCoreVersion")

val retrofit by extra("com.squareup.retrofit2:retrofit:$retrofitVersion")
val retrofitGson by extra("com.squareup.retrofit2:converter-gson:$retrofitVersion")
val threetenbp by extra("org.threeten:threetenbp:$threetenbpVersion")

val paging by extra("androidx.paging:paging-runtime:$pagingVersion")
val roomRuntime by extra("androidx.room:room-runtime:$roomVersion")
val roomKtx by extra("androidx.room:room-ktx:$roomVersion")
val roomCompiler by extra("androidx.room:room-compiler:$roomVersion")

val daggerRuntime by extra("com.google.dagger:dagger:$daggerVersion")
val daggerCompiler by extra("com.google.dagger:dagger-compiler:$daggerVersion")

val navigationFragment by extra("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
val navigationUi by extra("androidx.navigation:navigation-ui-ktx:$navigationVersion")
val navigationSafeArg by extra(
    "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
)
val navigationDynamicFeature by extra(
    "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"
)

val recyclerView by extra("androidx.recyclerview:recyclerview:$recyclerViewVersion")
val viewModel by extra("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
val liveData by extra("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
val coil by extra("io.coil-kt:coil:$coilVersion")

val sqlCipher by extra("net.zetetic:android-database-sqlcipher:$sqlCipherVersion")
val sqlKtx by extra("androidx.sqlite:sqlite:$sqlKtxVersion")

val leakCanary by extra("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")