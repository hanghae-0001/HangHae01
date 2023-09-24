import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    id("jacoco")
}

java {
    sourceCompatibility = JavaVersion.valueOf("VERSION_${property("javaVersion")}")

}

jacoco {
    toolVersion = "0.8.8"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val projectGroup: String by project
val applicationVersion: String by project
allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.register<Test>("unitTest") {
        useJUnitPlatform {
            excludeTags("integration")
        }
        extensions.configure(JacocoTaskExtension::class) {
            destinationFile = file("build/jacoco/jacoco-unit.exec")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }

        maxHeapSize = "2048m"
    }

    tasks.register<Test>("integrationTest") {
        useJUnitPlatform {
            includeTags("integration")
        }
        extensions.configure(JacocoTaskExtension::class) {
            destinationFile = file("build/jacoco/jacoco-integration.exec")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
        maxHeapSize = "2048m"
    }

    tasks.getByName<JacocoReport>("jacocoTestReport") {
        dependsOn("unitTest", "integrationTest")
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
        afterEvaluate {
            executionData.setFrom(
                files(
                    "$buildDir/jacoco/jacoco-unit.exec",
                    "$buildDir/jacoco/jacoco-integration.exec",
                ),
            )
        }
    }
}
