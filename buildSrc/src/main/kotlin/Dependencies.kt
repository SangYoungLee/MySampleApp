import Versions.kotlinVersion

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val androidApplication = "com.android.application"
}

object Versions {
    const val gradleVersion = "3.6.0"
    const val kotlinVersion = "1.3.71"

    const val minSdkVersion = 21
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29

    const val versionCode = 1
    const val versionName = "0.1"

    const val jetpack = "1.0.2"
    const val constraintLayout = "1.1.3"
    const val ktx = "1.1.0"
    const val coroutineVersion = "1.3.1"

    const val fragment = "1.1.0"

    const val lifecycle = "2.1.0"
    const val room = "2.2.0-rc01"
    const val navigation = "2.1.0"

    const val dagger = "2.24"

    const val moshi = "1.9.1"

    const val cameraX = "1.0.0-alpha05"
}

object Libraries {
    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat        = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"

    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.gradleVersion}"

    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val cameraXCore = "androidx.camera:camera-core:${Versions.cameraX}"
    const val cameraXCamera2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
}

object TestLibraries {
    private object TestVersions {
        const val junit4 = "4.12"
        const val androidXTest = "1.2.0"
        const val androidXTestExtKotlin = "1.1.1"

        const val fragmentTest = Versions.fragment

        const val espresso = "3.2.0"

        const val coroutine = Versions.coroutineVersion

        const val testArch = "2.0.0"

        const val mockito = "3.1.0"
    }
    const val junit4     = "junit:junit:${TestVersions.junit4}"
    const val testCore = "androidx.test:core:${TestVersions.androidXTest}"
    const val testCoreKtx   = "androidx.test:core-ktx:${TestVersions.androidXTest}"
    const val testRunner = "androidx.test:runner:${TestVersions.androidXTest}"
    const val testExtKotlin = "androidx.test.ext:junit-ktx:${TestVersions.androidXTestExtKotlin}"
    const val testArch = "androidx.arch.core:core-testing:${TestVersions.testArch}"

    const val fragmentTest = "androidx.fragment:fragment-testing:${TestVersions.fragmentTest}"

    const val espresso   = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"

    const val daggerCompiler = Libraries.daggerCompiler

    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${TestVersions.coroutine}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutine}"
    const val coroutineKotlinTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutine}"

    const val mockito = "org.mockito:mockito-core:${TestVersions.mockito}"
}