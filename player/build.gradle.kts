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

                //React, React DOM + Wrappers (chapter 3)
                implementation("org.jetbrains:kotlin-react:16.13.1-pre.110-kotlin-1.4.0-rc")
                implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.110-kotlin-1.4.0-rc")
                implementation(npm("react", "16.13.1"))
                implementation(npm("react-dom", "16.13.1"))

                //Kotlin Styled (chapter 3)
                implementation("org.jetbrains:kotlin-styled:1.0.0-pre.110-kotlin-1.4.0-rc")
                implementation(npm("styled-components", "5.1.1"))
                implementation(npm("inline-style-prefixer", "6.0.0"))

                //Video Player (chapter 7)
                implementation(npm("react-player", "2.6.0"))

                //Share Buttons (chapter 7)
                implementation(npm("react-share", "4.2.1"))

                //Coroutines (chapter 8)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.8-1.4.0-rc")
            }
        }
    }
}