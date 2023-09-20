import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("plugin.allopen") version "1.8.22"
    kotlin("plugin.noarg") version "1.8.22"
    id("jacoco")
}

allOpen {
    annotation("javax.persistence.Entity")
}

noArg {
    annotation("javax.persistence.Entity")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

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

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured:5.3.1")
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
        executionData.setFrom(files("$buildDir/jacoco/jacoco-unit.exec", "$buildDir/jacoco/jacoco-integration.exec"))
    }
}
