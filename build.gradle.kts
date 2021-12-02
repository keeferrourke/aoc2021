import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

val kotestVersion = "4.6.3"

dependencies {
    implementation(kotlin("script-runtime"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}