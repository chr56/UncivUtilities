import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}
repositories {
    mavenCentral()
    google()
}

tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("TranslationTemplateGenerator")
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "com.unciv.utilities.TranslationTemplateGeneratorKt"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("io.github.xn32:json5k:0.2.1") // json with comments support

    implementation("com.github.ajalt.clikt:clikt:3.5.1") // commandline parse
}