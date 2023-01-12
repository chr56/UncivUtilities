plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
}
repositories {
    mavenCentral()
    google()
}

kotlin {
}

dependencies{
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("io.github.xn32:json5k:0.2.1") // json with comments support
}