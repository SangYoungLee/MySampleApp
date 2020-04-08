allprojects {
    repositories {
        jcenter()
        google()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}