allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    compileOnly(project(":commerce-api"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.github.f4b6a3:ulid-creator:${property("ulidCreatorVersion")}")
    runtimeOnly("com.mysql:mysql-connector-j")
}
