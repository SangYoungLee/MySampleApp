
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
        applicationId = "com.syapp.todo"
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":base_component"))

    "implementation"(Libraries.kotlinStdLib)
    "implementation"(Libraries.appCompat)
    "implementation"(Libraries.ktxCore)
    "implementation"(Libraries.constraintLayout)

    "kapt"(Libraries.dataBindingCompiler)

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

    "implementation"(Libraries.rxJava)
    "implementation"(Libraries.rxAndroid)
    "implementation"(Libraries.rxPermissions)

    "implementation"(Libraries.timber)

    /*************** Test Libraries *****************/

    "implementation"(Libraries.fragment)
    "implementation"(Libraries.fragmentKtx)
    "implementation"(TestLibraries.fragmentTest)

    "androidTestImplementation" (Libraries.appCompat)

    "testImplementation" (TestLibraries.junit4)
    "androidTestImplementation" (TestLibraries.junit4)

    "implementation" (TestLibraries.testCore)
    "testImplementation" (TestLibraries.testCoreKtx)
    "androidTestImplementation" (TestLibraries.testCoreKtx)

    "testImplementation" (TestLibraries.testRunner)
    "androidTestImplementation" (TestLibraries.testRunner)

    "testImplementation" (TestLibraries.testExtKotlin)
    "androidTestImplementation" (TestLibraries.testExtKotlin)

    "testImplementation" (TestLibraries.testArch)

    "testImplementation" (TestLibraries.espresso)
    "androidTestImplementation" (TestLibraries.espresso)

    "testImplementation" (TestLibraries.coroutineTest)
    "testImplementation" (TestLibraries.coroutineAndroid)
    "androidTestImplementation" (TestLibraries.coroutineKotlinTest)

    "kaptAndroidTest" (TestLibraries.daggerCompiler)
    "kaptTest" (TestLibraries.daggerCompiler)

    "testImplementation" (TestLibraries.mockito)
    "androidTestImplementation" (TestLibraries.mockito)
}