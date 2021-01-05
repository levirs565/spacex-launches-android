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

val kotlin by rootProject.extra( "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
val kotlinPlugin by rootProject.extra("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
val kotlinCoroutineCore by rootProject.extra( "org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinCoroutineVersion}")

val coreKtx by rootProject.extra( "androidx.core:core-ktx:${coreKtxVersion}")
val appCompat by rootProject.extra( "androidx.appcompat:appcompat:${appCompatVersion}")
val material by rootProject.extra( "com.google.android.material:material:${materialVersion}")
val constraintLayout by rootProject.extra( "androidx.constraintlayout:constraintlayout:${constraintLayoutVersion}")
val junit by rootProject.extra( "junit:junit:${junitVersionVersion}")
val extJunit by rootProject.extra( "androidx.test.ext:junit:${extJunitVersion}")
val espressoCore by rootProject.extra( "androidx.test.espresso:espresso-core:${espressoCoreVersion}")

val retrofit by rootProject.extra( "com.squareup.retrofit2:retrofit:${retrofitVersion}")
val retrofitGson by rootProject.extra( "com.squareup.retrofit2:converter-gson:${retrofitVersion}")
val threetenbp by rootProject.extra( "org.threeten:threetenbp:${threetenbpVersion}")

val paging by rootProject.extra( "androidx.paging:paging-runtime:${pagingVersion}")
val roomRuntime by rootProject.extra( "androidx.room:room-runtime:${roomVersion}")
val roomKtx by rootProject.extra( "androidx.room:room-ktx:${roomVersion}")
val roomCompiler by rootProject.extra( "androidx.room:room-compiler:${roomVersion}")

val daggerRuntime by rootProject.extra( "com.google.dagger:dagger:${daggerVersion}")
val daggerCompiler by rootProject.extra( "com.google.dagger:dagger-compiler:${daggerVersion}")

val navigationFragment by rootProject.extra( "androidx.navigation:navigation-fragment-ktx:${navigationVersion}")
val navigationUi by rootProject.extra( "androidx.navigation:navigation-ui-ktx:${navigationVersion}")
