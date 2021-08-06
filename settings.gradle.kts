pluginManagement {

    val kotlinVersion: String by settings
    val spotlessVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.diffplug.spotless") version spotlessVersion
    }
}
rootProject.name = "KChat"
