
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
    id(BuildPlugins.androidApplication)
//    id("org.jetbrains.kotlin.android")
//    id("org.jetbrains.kotlin.android.extensions")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

configure<BaseAppModuleExtension> {
    setCompileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = "com.example.mysampleapp"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = false
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {

    "implementation"(Libraries.kotlinStdLib)
    "implementation"(Libraries.appCompat)
    "implementation"(Libraries.ktxCore)
    "implementation"(Libraries.constraintLayout)

    "implementation"(Libraries.lifecycle)
    "kapt"(Libraries.lifecycleCompiler)
    "implementation"(Libraries.viewModel)

    "implementation"(Libraries.room)
    "kapt"(Libraries.roomCompiler)
    "implementation"(Libraries.roomKtx)

    "implementation"(Libraries.navigationFragment)
    "implementation"(Libraries.navigationUI)

    "testImplementation" (TestLibraries.junit4)
    "androidTestImplementation" (TestLibraries.testRunner)
    "androidTestImplementation" (TestLibraries.espresso)
}