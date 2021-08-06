plugins {
    application
    kotlin("jvm")
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("${project.projectDir}/spotless.license")
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

group = "com.keygenqt.kchat"
version = "0.0.1"

application {
    mainClass.set("com.keygenqt.kchat.ApplicationKt")
}

repositories {
    mavenCentral()
}

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

dependencies {
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
