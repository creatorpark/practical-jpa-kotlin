import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


// Kotlin JPA With Query DSL
// https://v3.leedo.me/devs/118
plugins {
    val kotlinVersion = "1.9.22"
    var springBootVersion = "3.2.2"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.1.4"
    // kapt는 자바로 작성되어 있으므로 id로 적용됨
    id("org.jetbrains.kotlin.kapt") version kotlinVersion

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "sample"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

allOpen {
    // All Open Plug-in 사용 후에 여기에 해당 All Open 이름을 적는다.
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")

    // Project Structure - Facets - Kotlin - compiler Options
    // 에서 추가된 목록을 확인 할 수 있다.
    // all open이 적용된 후에 Entity를 Decompile한 뒤 자바 소스로
    // public final [ClassName] -> public [ClassName]으로 변경 된다.
}

dependencies {
    val kotlin = "1.9.22"

    val log = "5.0.0-beta-02"

    val queryDsl = "5.0.0"

    val koTestSpring = "1.1.3"
    val koTest = "5.8.0"

    kapt("com.querydsl:querydsl-apt:${queryDsl}:jakarta")

    // Domain
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:${queryDsl}:jakarta")
    implementation("com.h2database:h2")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")

    implementation("io.hypersistence:hypersistence-utils-hibernate-62:3.5.1")
    // infra
    // https://github.com/FasterXML/jackson-module-kotlin
    // jackson-module-kotlin 버전과 kotlin-reflect 버전을 맞춰야함.
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlin}")

    implementation("io.github.oshai:kotlin-logging-jvm:${log}")
    // TEST
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:${koTestSpring}")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${koTest}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    exclude("**/*")
}
