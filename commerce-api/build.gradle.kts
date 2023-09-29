tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":commerce-support:logging"))
    implementation(project(":commerce-support:monitoring"))
    runtimeOnly(project(":commerce-infra:db-main"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    testRuntimeOnly("com.h2database:h2")
}
