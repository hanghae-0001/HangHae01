tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":commerce-support:logging"))
    implementation(project(":commerce-support:monitoring"))
    runtimeOnly(project(":commerce-infra:db-main"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    testRuntimeOnly("com.h2database:h2")
}
