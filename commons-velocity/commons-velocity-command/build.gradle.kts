import org.jetbrains.kotlin.config.KotlinCompilerVersion
plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.github.johnrengelman.shadow")
    id("maven")
    id("maven-publish")

}

repositories {
    maven("https://nexus.velocitypowered.com/repository/velocity-artifacts-snapshots/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:1.0.11-SNAPSHOT")
    implementation(project(":commons-command"))
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    dependencies {
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-common"))

        include(dependency(":commons-command"))
    }
    classifier = null
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("shadow") {
            shadow.component(this)
        }
    }
}


/*
publishing {
    publications {
        create<MavenPublication>(project.name) {
            project.shadow.component(this)
        }
    }

    publications.withType<MavenPublication> {
        project.shadow.component(this)
    }
}*/