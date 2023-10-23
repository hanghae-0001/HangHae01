tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":commerce-support:logging"))
    implementation(project(":commerce-support:monitoring"))
    runtimeOnly(project(":commerce-infra:db-main"))
    runtimeOnly(project(":commerce-infra:redis-main"))
    runtimeOnly(project(":commerce-infra:external-api"))

    compileOnly("org.springframework:spring-tx")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("io.github.resilience4j:resilience4j-spring-boot2:2.1.0")

    testRuntimeOnly("com.h2database:h2")
    testImplementation("org.testcontainers:testcontainers:1.17.6")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
    testImplementation("io.mockk:mockk:1.12.5")
    testImplementation("com.redis.testcontainers:testcontainers-redis-junit-jupiter:1.4.6")
}
