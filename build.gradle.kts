plugins {
    application
    kotlin("jvm")
    id("com.diffplug.spotless")
    kotlin("plugin.serialization")
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

val koinVersion: String by project
val ktorVersion: String by project
val kotlinVersion: String by project
val flywayVersion: String by project
val exposedVersion: String by project
val logbackVersion: String by project
val hikariCpVersion: String by project
val mysqlConnectorVersion: String by project
val firebaseAdminVersion: String by project

dependencies {
    // ktor
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")

    // exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    // di
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    // other
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("com.google.firebase:firebase-admin:$firebaseAdminVersion")

    // db
    implementation("mysql:mysql-connector-java:$mysqlConnectorVersion")
    implementation("com.zaxxer:HikariCP:$hikariCpVersion")

    // tests
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
