pluginManagement {
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "confexplorer"

include("player")
include("app")
