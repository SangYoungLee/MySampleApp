buildscript {

    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
    }
}


allprojects {
    repositories {
        jcenter()
        google()
    }
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-base`
    `kotlin-dsl-precompiled-script-plugins`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.android.tools.build:gradle:3.5.0")
}