plugins {
    application
    kotlin("jvm")
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
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}