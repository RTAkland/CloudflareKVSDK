import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.20"
    id("maven-publish")
}

val libVersion: String by project

group = "cn.rtast"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")
    api("com.google.code.gson:gson:2.11.0")
}

kotlin {
    jvmToolchain(8)
}

val sourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

artifacts {
    archives(sourceJar)
}

tasks.jar {
    from("LICENSE") {
        rename { "ROneBot-LICENSE-Apache2.0" }
    }
}

tasks.compileKotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_1_8
}

tasks.compileJava {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourceJar)
            artifactId = "CloudflareKVSDk"
            version = libVersion
        }
    }

    repositories {
        maven {
            url = uri("https://repo.rtast.cn/api/v4/projects/43/packages/maven")
            credentials {
                username = "RTAkland"
                password = System.getenv("TOKEN")
            }
        }
    }
}