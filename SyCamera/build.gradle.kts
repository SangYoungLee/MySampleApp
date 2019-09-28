
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
    id(BuildPlugins.androidApplication)
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

configure<BaseAppModuleExtension> {
    setCompileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = "com.syapp.sycamera"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "com.syapp.todo.CustomTestRunner"
    }

    sourceSets {
        val sharedDir = File("src/sharedTest/java")
        getByName("test") {
            java.srcDirs(sharedDir)
        }

        getByName("androidTest") {
            java.srcDirs(sharedDir)
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    "implementation"(Libraries.kotlinStdLib)
    "implementation"(Libraries.appCompat)
    "implementation"(Libraries.ktxCore)
    "implementation"(Libraries.constraintLayout)

    "kapt"(Libraries.dataBindingCompiler)

    "implementation"(Libraries.lifecycle)
    "kapt"(Libraries.lifecycleCompiler)
    "implementation"(Libraries.viewModel)
}
