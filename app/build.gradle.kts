plugins {
    kotlin("js")
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

kotlin {
    js {
        browser {}
    }

    sourceSets {
        val main by getting {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                implementation(kotlin("stdlib-js"))

                // Player
                implementation(project(":player"))

                //React, React DOM + Wrappers (chapter 3)
                implementation("org.jetbrains:kotlin-react:16.13.1-pre.110-kotlin-1.4.0-rc")
                implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.110-kotlin-1.4.0-rc")
                implementation(npm("react", "16.13.1"))
                implementation(npm("react-dom", "16.13.1"))
            }
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xir-loaded-lazily=<org.example:player>"
    kotlinOptions.moduleKind = "commonjs"
}