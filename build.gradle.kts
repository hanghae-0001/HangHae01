import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22" apply false
    kotlin("plugin.jpa") version "1.8.22" apply false
    id("org.springframework.boot") version "3.1.3" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
    id("jacoco")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

jacoco {
    toolVersion = "0.8.8"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
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
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
        compileOnly("org.projectlombok:lombok")
        runtimeOnly("com.mysql:mysql-connector-j")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("com.h2database:h2")
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
