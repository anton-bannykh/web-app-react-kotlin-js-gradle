plugins {
//    kotlin("js") version "1.4.255-SNAPSHOT"
    kotlin("js") version "1.4.20-dev-3529"
//    id("org.jetbrains.kotlin.js") version "1.4.0-rc"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev") }
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
    mavenLocal()
}

tasks {
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
        gradleVersion = "6.1.1"
    }
}

kotlin {
    js {
        binaries.executable()

        browser {}
    }

    sourceSets {
        val main by getting {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                implementation(kotlin("stdlib-js"))

                // React app
                implementation(project(":app"))

                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1-1.4.0-rc")
            }
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xir-per-module"
    kotlinOptions.freeCompilerArgs += "-Xir-loaded-lazily=<org.example:app>"
}

