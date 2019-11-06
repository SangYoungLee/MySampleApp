plugins {
    id(BuildPlugins.androidApplication)
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    setCompileSdkVersion(Versions.compileSdkVersion)


    defaultConfig {
        applicationId = "com.syapp.componentbasicapp"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        isEnabledForTests = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(project(":base_component"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    "implementation"(Libraries.kotlinStdLib)
    "implementation"(Libraries.appCompat)
    "implementation"(Libraries.ktxCore)
    "implementation"(Libraries.constraintLayout)

    kapt(Libraries.dataBindingCompiler)

    "implementation"(Libraries.lifecycle)
    "kapt"(Libraries.lifecycleCompiler)
    "implementation"(Libraries.viewModel)

    "implementation"(Libraries.room)
    "kapt"(Libraries.roomCompiler)
    "implementation"(Libraries.roomKtx)

    "implementation"(Libraries.navigationFragment)
    "implementation"(Libraries.navigationUI)

    "implementation"(Libraries.coroutine)

    "implementation"(Libraries.dagger)
    "kapt"(Libraries.daggerCompiler)
    "implementation"(Libraries.daggerAndroidSupport)
    "kapt"(Libraries.daggerAndroidProcessor)

    "implementation"(Libraries.timber)

    "androidTestImplementation" (Libraries.appCompat)

    "testImplementation" (TestLibraries.junit4)
    "androidTestImplementation" (TestLibraries.junit4)
}
