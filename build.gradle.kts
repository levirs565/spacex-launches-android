// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    apply(from = "dependencies.gradle.kts")
    val kotlinPlugin: String by extra
    val navigationSafeArg: String by extra

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath(kotlinPlugin)
        classpath(navigationSafeArg)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
