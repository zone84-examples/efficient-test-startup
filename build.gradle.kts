plugins {
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("io.micronaut.application") version "3.1.1"
}

version = "0.1"
group = "tech.zone84.examples.efficientteststartup"

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("tech.zone84.examples.efficientteststartup.*")
    }
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.mongodb:micronaut-mongo-sync")

    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.testcontainers:testcontainers:1.16.3")
    testImplementation("org.testcontainers:mongodb:1.16.3")
}

application {
    mainClass.set("tech.zone84.examples.efficientteststartup.Application")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
