dependencies {
    compileOnly(project(":commerce-api"))

    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}
